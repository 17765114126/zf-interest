package com.example.springboot.model.sys;

import lombok.Data;

import java.util.List;

/**
 * Created by chen on 2019/7/2.
 */
@Data
public class Meta {

    private String title;

//    private boolean requireAuth = true;
//
//    private boolean NoTabPage = false;

    private List<BtnPermissions> btnPermissions;

    private boolean keepAlive = false;

}
