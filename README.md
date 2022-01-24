# EmailServier

## Goal

Send an email selecting from which account the user wants to send it, so he won't need to login every time in every account he wants to use.

## Main parts

In order to preserve the security, i implemented an independent module -**emailserver-authentication** to request for a token, using the following request:

```
curl --location --request POST 'localhost:9090/login' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' \
--header 'Content-Type: application/json' \
--data-raw '{
    "user":"admin",
    "password":"admin"
}'
```
I simplfyied the username signed in, so there are just one possible user called **admin**. After getting the token, it will be possible to request the main module
**emailserver** for sending and loading emails.
