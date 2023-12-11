package com.example.biyeboot.controller;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.example.biyeboot.entity.AliPay;

import com.example.biyeboot.entity.JsonResult;
import com.example.biyeboot.mapper.OrderdetailMapper;
import com.example.biyeboot.mapper.RefundMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class AliPayController {
    @Autowired
    OrderdetailMapper orderdetailMapper;
    @Autowired
    RefundMapper refundMapper;
    @GetMapping("/pay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    public String pay(AliPay aliPay) {
        AlipayTradePagePayResponse response;
        try {
            //  发起API调用（以创建当面付收款二维码为例）
            response = Factory.Payment.Page()
                    .pay(URLEncoder.encode(aliPay.getSubject(), "UTF-8"), aliPay.getTraceNo(), String.valueOf(aliPay.getTotalAmount()), "");
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        return response.getBody();
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
                // 更新订单未已支付
            }
            orderdetailMapper.alterPayState(Integer.valueOf(params.get("out_trade_no")));
            orderdetailMapper.insertTradeNo(params.get("trade_no"),Integer.valueOf(params.get("out_trade_no")));

        }
        return "success";
    }
    @GetMapping("/refundMoney")
    public JsonResult refundMoney(@RequestParam(defaultValue = "") String tradeNo, @RequestParam Integer refundAmount,@RequestParam int orderId){
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi-sandbox.dl.alipaydev.com/gateway.do",
                "2021000122673013",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCm+msVT+FtTlVV94lBsSQ7ORnUBOh3Z+El16hDUXvmvbhEIxHsH4F2kNOTgT/0Du+6Eqq6eabJ3r6ecCx7jYKxRhLpUyCDMmLWktcftektUH7dwYYY1v7bBC5jl3VjrucnVCYr/237RmypPfgqmf6fED4a4KomKKHX4A29v5/YKdn1jqaLC9aYK3/4K8WMFo4yM20kHmZROAJC5j5UEeT7oL/e2fnwvugg5kK+h0n7ZG4KK1N6ljlLt1kXQWej9d/oZtXQq1unlnjDUKZsB+91hmSseB+jrvdOOR4stvKOxAazhWUtMVWp/x7zY0/FZVVMgit0lsdZ0K7dkWNE3+G3AgMBAAECggEAY3plOXkxj/FPHbwvZZenQjEulgWmiRy2IdltQU3WRw1YLh/Gmanbc0utAP/LnhcX8v8T/6xiFXpCjhm+Jo2UAxEAS0le4UqvX65zw0yY8oo2St70iO/tPYYsLOEa6pxU0jVr2GKAn2bBbcKhip/b9d+zYEfgloM2iGnPRfBXxmuOaAsMLW7EqRn5N4z1hkCHhBlUC3ShH5E60VCF6yK9w9nQEXpB/XQPZnqGtv8ILAsqRC4B8BquXQs8EQlg0zU8dlzexC4noV/ZQQPIQsFh0Yu0syIkEB3IzhxueIJAZ2BZHPQsHbz+6xv34z2sM7x9O29C9katPMqYID1loQk8AQKBgQD2e5dXNCcMq1rBc7oqJ2aV6NDHvo/nlvN00mBxBKB3A1vmAmdCkSbDTaQLYS3tSg04DxINFBhFlAknSybDmi4cyt0c0SzFK3OAzm+qp7mjWdXax+ZVWxyuRZdEWRR0X/zRc0DE1XNaMWV7gPY0cEdD3VijAf12C3cd5A8ciMl+gQKBgQCtbPKxSx/xWEaQ7Nam4sXAtTl4ElWhqRLjtYed3kUgZmyjPD1LttZmleARU2XMjmJ5JuwFFOwqy65YN8F1Fp4x37Zulezs4Q+R2ZHmt12BTZ2P2pxkHX6wFF+tQtuimeIxNB3bKC+Dj7aZdbG++oaF7ywPsHUMkazbTyElaSO0NwKBgQCM8y4fQwaVmyPB/pDV7EQgs8s4fkPClrLP+kIGPzQX+sbBqFwuV90H0E1f6Imf6TnX9d5HtlcO6tG1M6eOMYvvER8SjJeChQ9++A4pU9K3k/bULn4MD5c+HTOcdShaSE3F5JeEN9/IEvlKf/vLDu3h5CD8znWH3JSYdkMh+SLogQKBgEs1VSZH4wlh8/BDlW74JzREIzEMGnCqBtQTVEXFL4s2f+RmalqcZuxEENDSrvH/yXqqw24MvcJtZCVDwLTLYz1TvCTHBeFQVNU2WkyItpctlqfc8TeFhvxLSZa0yNUCERKvfEcfbUOLnm98l+GveYk5yIzhRFF4F1NgRIqZxu8PAoGBAKsLhAh/XVP6qx8hBK+KWHT/NhhgO757J5w0++NILZHhHaLIUR6nb3alj1xSKpaR/I+NrpkuCUJmlif9QcmhbirQIE85golUVAPMTcbdtUcqh95clGtFtquX/RcGTkUcCw+tA78bnCtBFMcL6mOdsyETnhDMKcHLXfjyE+Mughy6",
                "json","GBK",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlCJhyKTS0l1juuZaalra33GTRNS4Sq+LPIri/N2zCRV6s083RGcPFTEp2XRkm/hUOja35pC3xjPeEF608QrBjJFUOQ6qBcFwuHL1EGDttAlW677qP4/ui+PtR+ybfaJryJlRMvYgWbvcZqeC2/Ufha2OktdKZ4vYZwTwOd2QgNqDp5izFETPeYbZsGarvWmXcb8V3bJJIT2YUDGf8HdZUEuHhB58v9gyt41EQhetao2391xCUXOGKuEC10aqIf328c4Tr7i7KLgeTPTRhgMoNNBYSore3NdphMZx+rEOY1f6sO3tvNjIopqLVljcPZosQ/+f6BMzZxj/swIqwotQvwIDAQAB",
                "RSA2");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("trade_no", tradeNo);
        bizContent.put("refund_amount",refundAmount);
//        bizContent.put("out_request_no", "HZ01RF001");

//// 返回参数选项，按需传入
//JSONArray queryOptions = new JSONArray();
//queryOptions.add("refund_detail_item_list");
//bizContent.put("query_options", queryOptions);

        request.setBizContent(bizContent.toString());
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            refundMapper.alterState(orderId);
            return new JsonResult<>(200);
        } else {
            System.out.println(response.getBody());
            System.out.println("调用失败");
            return new JsonResult<>(201);
        }

    }
}

