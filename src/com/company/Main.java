package com.company;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    //public static User user;
    // public static User userPassword;
    // public static ArrayList<Messages> messages = new ArrayList();

    public static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    HashMap m = new HashMap<>();
                    if (user == null || user.name.isEmpty() || user.password.isEmpty()) {
                        return new ModelAndView(m, "login.html");
                    } else {
                        m.put("name", name;
                        m.put("newMessages", user.messages);
                        return new ModelAndView(m, "home.html");
                    }

                }),
                new MustacheTemplateEngine()

        );


        Spark.post(
                "/login",
                ((request, response) -> {

                    String userName = request.queryParams("loginName");
                    String userPassword = request.queryParams("loginPassword");
                    User newUser = users.get(userName);


                    if (newUser == null) {
                        newUser = new User(userName, userPassword);
                        users.put(userName, newUser);

                    }

                    Session session = request.session();
                    session.attribute("loginName", newUser);


//
//                    // common mistake being made. when you are checking
//                    // values, you are getting the value from the "object" and using it through
//                    // a created vehicle. not:XXUser user = new User(userName);XX
//                    //User password = new User(userPassword);
//                    if(user.containskey(name) && userPassword.equals(password)) {
//
//                        return new ModelAndView(users, "index.html");
//
//                    } else {
//                        //old.put("password", user.userPassword);
//                        users.put("name", user.name);
//                        users.put("newMessage", user.messages);
//                        users.put("loginPassword", user.password);

                    response.redirect("/");
                    return "";
                })

        );


        Spark.post(
                "/editMessages",
                ((request, response) -> {

                    Session session = request.session();
                    String message = session.attribute("loginName");
                    User user = user.get(message);

                    if (user == null) {
                        throw new Exception("no user");
                    }


                    String edit = request.queryParams("newNumb");
                    int talk = Integer.parseInt(edit) - 1;
                    // messages.add(newMess);

                    user.messages.remove(talk);
                    String editor = request.queryParams("messageEditor");
                    Messages me = new Messages(editor);

                    user.messages.add(talk, me);

                    response.redirect("/");
                    return "";
                })
        );


        Spark.post(
                "/create-message",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("loginName");
                    User user = users.get(name);
                    if (user == null) {
                        throws new Exception:"no user found"
                    }
                    String message = request.queryParams("message");
                    Messages messages = new Messages(message);
                    user.messages.add(messages);
                    //String name = request.queryParams("loginName");
                    response.redirect("/");
                    return "";

                }));
    }
        Spark.post(
                "/deleteMessage",
                ((request, response) ->

    {

        Session session = request.session();
        String mess = session.attribute("loginName");
        User user = users.get(mess);

        if (user == null) {
            throw new Exception("no user");
        }


        String delete = request.queryParams("deleteMessage");
        int e = Integer.parseInt(delete) - 1;

        user.messages.remove(e);

        response.redirect("/");
        return "";
    }













