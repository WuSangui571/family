package com.haoze.javaweb.bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.util.Objects;

/**
 * @author 寿豪泽
 */
public class Family  implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 用户登录了
        ServletContext application = event.getSession().getServletContext();
        // 获取在线人数
        Object onlineCount = application.getAttribute("onlineCount");
        if (onlineCount == null) {
            application.setAttribute("onlineCount",1);
        }else{
            int count = ((Integer)onlineCount);
            count++;
            application.setAttribute("onlineCount",count);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 用户退出了
        ServletContext application = event.getSession().getServletContext();
        int onlineCount = (Integer)application.getAttribute("onlineCount");
        onlineCount--;
        application.setAttribute("onlineCount",onlineCount);
    }

    private String name;

    @Override
    public String toString() {
        return "Family{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Family family = (Family) o;
        return Objects.equals(name, family.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Family() {
    }

    public Family(String name) {
        this.name = name;
    }
}
