package com.yspjt.dbafe.Model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

//  userData  
@Data
public class SigModel {

    private String user_id;
    private String user_pass;
    private String user_name;
    private String user_address;
    private String user_phone;
    private int user_state;
    private int user_code;

    private Date user_dob;
    private double user_height;
    private int user_gender;
    private int user_telecom_provider;

    // userDataSeb;
    private Date user_data_insert_date;
    private double user_weight;
    private double user_bmi;

}
