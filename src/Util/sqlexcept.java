/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author user
 */
public class sqlexcept extends Exception{
    private String msg;
    public sqlexcept(String m){
        this.msg=m;
    }
    public String getMsg()
    {
        return this.msg;
    }
}