POST
http://localhost:8095/IdeaAutoServlet_war_exploded/users

{
    "firstName": "John",
    "lastName":"Snow",
    "birthDay":"2000-01-05",
    "company":"Nikelodeon"
}

GET
http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=0

PUT
http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=0&company=JamesCo

DELETE
http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=0

