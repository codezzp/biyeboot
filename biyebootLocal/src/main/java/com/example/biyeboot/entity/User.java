package com.example.biyeboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author jojo
 * @since 2023-03-02
 */
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String username;

    private String password;

    private String phone;

    private String address;

    private LocalDate createTime;

    private String avatarUrl;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getUsername() {
        return username;
    }

      public void setUsername(String username) {
          this.username = username;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public String getPhone() {
        return phone;
    }

      public void setPhone(String phone) {
          this.phone = phone;
      }
    
    public String getAddress() {
        return address;
    }

      public void setAddress(String address) {
          this.address = address;
      }
    
    public LocalDate getCreateTime() {
        return createTime;
    }

      public void setCreateTime(LocalDate createTime) {
          this.createTime = createTime;
      }
    
    public String getAvatarUrl() {
        return avatarUrl;
    }

      public void setAvatarUrl(String avatarUrl) {
          this.avatarUrl = avatarUrl;
      }

    @Override
    public String toString() {
        return "User{" +
              "id=" + id +
                  ", username=" + username +
                  ", password=" + password +
                  ", phone=" + phone +
                  ", address=" + address +
                  ", createTime=" + createTime +
                  ", avatarUrl=" + avatarUrl +
              "}";
    }
}
