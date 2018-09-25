# ignorant-chat
do not rush yourself when you are getting bad.

To make progress in English, I use my crappy grammar to take this note. If you don't like it, you can Alt+F4, thanks for you cooperations.
Spring Boot was amazing so we use it to create a restful(maybe un-restful) api.

## LOGIN

We prepare to use **Mysql** to save user info because of it's properties of strong association with other data. **Redis** will be used to store user's session and status of online or offline, as a result of it's TTL feature and excellent performance. AOP is suitable to valid a api request and verify information of user.

## COMMUNICATION

 To save large amount of message data and meet the frequent access requirement, we use **MongoDB**. Actually, message data does not like general relational structure, it's more like NoSQL structure. On the other hand, I want to try it because I haven't used it yet.