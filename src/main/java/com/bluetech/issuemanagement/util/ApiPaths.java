package com.bluetech.issuemanagement.util;
/*
 * Created by yasinkilinc on 10.11.2020
 */

public interface ApiPaths {

    String BASE_PATH = "/api";

    interface IssueCtrl{
        String CTRL = BASE_PATH + "/issue" ;
    }

    interface ProjectCtrl{
        String CTRL = BASE_PATH + "/project" ;
    }
}
