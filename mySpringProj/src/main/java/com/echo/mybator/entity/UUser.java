package com.echo.mybator.entity;

import com.echo.commons.base.BaseEntity;
import java.util.Date;

public class UUser extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.id_user
     *
     * @mbggenerated
     */
    private String idUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.toa_client_no
     *
     * @mbggenerated
     */
    private String toaClientNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.register_source
     *
     * @mbggenerated
     */
    private String registerSource;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.register_phone
     *
     * @mbggenerated
     */
    private String registerPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.register_time
     *
     * @mbggenerated
     */
    private Date registerTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.is_id_checked
     *
     * @mbggenerated
     */
    private Boolean isIdChecked;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.is_work_checked
     *
     * @mbggenerated
     */
    private Boolean isWorkChecked;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.init_basic_status
     *
     * @mbggenerated
     */
    private String initBasicStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.init_label_status
     *
     * @mbggenerated
     */
    private String initLabelStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.img_head
     *
     * @mbggenerated
     */
    private String imgHead;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.last_login_date
     *
     * @mbggenerated
     */
    private Date lastLoginDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.last_login_device
     *
     * @mbggenerated
     */
    private String lastLoginDevice;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated
     */
    public UUser(String idUser, String userName, String toaClientNo, String registerSource, String registerPhone, Date registerTime, Boolean isIdChecked, Boolean isWorkChecked, String initBasicStatus, String initLabelStatus, String imgHead, Date lastLoginDate, String lastLoginDevice) {
        this.idUser = idUser;
        this.userName = userName;
        this.toaClientNo = toaClientNo;
        this.registerSource = registerSource;
        this.registerPhone = registerPhone;
        this.registerTime = registerTime;
        this.isIdChecked = isIdChecked;
        this.isWorkChecked = isWorkChecked;
        this.initBasicStatus = initBasicStatus;
        this.initLabelStatus = initLabelStatus;
        this.imgHead = imgHead;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginDevice = lastLoginDevice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.id_user
     *
     * @return the value of u_user.id_user
     *
     * @mbggenerated
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.user_name
     *
     * @return the value of u_user.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.toa_client_no
     *
     * @return the value of u_user.toa_client_no
     *
     * @mbggenerated
     */
    public String getToaClientNo() {
        return toaClientNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.register_source
     *
     * @return the value of u_user.register_source
     *
     * @mbggenerated
     */
    public String getRegisterSource() {
        return registerSource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.register_phone
     *
     * @return the value of u_user.register_phone
     *
     * @mbggenerated
     */
    public String getRegisterPhone() {
        return registerPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.register_time
     *
     * @return the value of u_user.register_time
     *
     * @mbggenerated
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.is_id_checked
     *
     * @return the value of u_user.is_id_checked
     *
     * @mbggenerated
     */
    public Boolean getIsIdChecked() {
        return isIdChecked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.is_work_checked
     *
     * @return the value of u_user.is_work_checked
     *
     * @mbggenerated
     */
    public Boolean getIsWorkChecked() {
        return isWorkChecked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.init_basic_status
     *
     * @return the value of u_user.init_basic_status
     *
     * @mbggenerated
     */
    public String getInitBasicStatus() {
        return initBasicStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.init_label_status
     *
     * @return the value of u_user.init_label_status
     *
     * @mbggenerated
     */
    public String getInitLabelStatus() {
        return initLabelStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.img_head
     *
     * @return the value of u_user.img_head
     *
     * @mbggenerated
     */
    public String getImgHead() {
        return imgHead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.last_login_date
     *
     * @return the value of u_user.last_login_date
     *
     * @mbggenerated
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.last_login_device
     *
     * @return the value of u_user.last_login_device
     *
     * @mbggenerated
     */
    public String getLastLoginDevice() {
        return lastLoginDevice;
    }
}