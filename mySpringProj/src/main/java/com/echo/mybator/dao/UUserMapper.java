package com.echo.mybator.dao;

import com.echo.mybator.entity.UUser;

public interface UUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String idUser);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated
     */
    int insert(UUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated
     */
    int insertSelective(UUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated
     */
    UUser selectByPrimaryKey(String idUser);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UUser record);
}