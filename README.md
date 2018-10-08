# ignorant-chat
do not rush yourself when you are getting bad.

To make progress in English, I use my crappy grammar to take this note. If you don't like it, you can Alt+F4, thanks for you cooperations.
Spring Boot was amazing so we use it to create a restful(maybe un-restful) api.

## ACCOUNT

>Now we consider that it need to keep the status of a user(on-line or off-line), so it's maybe more suitable for using **Redis**.

~~To keep the identity of you, we use shiro, it provides permission management which maybe used in last version of ignorant-chat, from Apache Foundation and~~ use **Mysql** to store your information because of it's properties of strong association with other data. 

## LOGIN

**Redis** will be used to store user's session and status of on-line or off-line, as a result of it's TTL feature and excellent performance. AOP is suitable to valid a api request and verify information of user, we use filter to realize this. We use two collections, one will record the relation of session-id to user-id, the other one will record the relation user-id to all of it's friends and we will notify this users when user's information changed, both will get TTL and AOP will used to update last modify date of the second collection when session-user changed. Using publish/subscription to catch the overtime event of a document of the second collection and tell all of her friends who was in it's values.

## COMMUNICATION

 To save large amount of message data and meet the frequent access requirement, we use **MongoDB**. Actually, message data does not like general relational structure, it's more like No-SQL structure. On the other hand, I want to try it because I haven't used it yet. 