# KakaoTalk

## Chatting protocol

* use protocol.java file

### `packet format for make chatting room`

|        type        |     name      |       description        |
|:------------------:|:-------------:|:------------------------:|
|        int         | typeofrequest |  type of request number  |
|       String       |    sender     | user_id of requst member |
| ArrayList\<String> |     list      |   invite chat members    |


### `packet format for invite chatting room`

|        type        |     name      |        description        |
|:------------------:|:-------------:|:-------------------------:|
|        int         | typeofrequest |  type of request number   |
|       String       |    sender     | user_id of requst member  |
|       String       |  roomnumber   | chat room number with md5 |
| ArrayList\<String> |     list      |    invite chat members    |

### `packet format for remove chatting room and recall chatting room participant list and find id`

|  type  |     name      |          description          |
|:------:|:-------------:|:-----------------------------:|
|  int   | typeofrequest |    type of request number     |
| String |    sender     |   user_id of requst member    |
| String |  roomnumber   |  chat room number with md5    |
| String |     email     |    email of requst member     |
| String |     phone     | phone number of requst member |

### `packet format for send message`

|  type   |     name      |                description                |
|:-------:|:-------------:|:-----------------------------------------:|
|   int   | typeofrequest |          type of request number           |
| String  |  roomnumber   |         chat room number with md5         |
| String  |    sender     |         user_id of requst member          |
| String  |    message    |              message content              |
| String  |     time      |          time when send messege           |
| boolean |  file_exist   |        Determine if a file exists         |
| String  |   file_path   | Storage Location of sended file in server |

### `request of chat room list and logout and change passward and request self information`
|  type  |     name      |       description        |
|:------:|:-------------:|:------------------------:|
|  int   | typeofrequest |  type of request number  |
| String |    sender     | user_id of requst member |
| String |   passward    | user_pw of requst member |

### `confirm for passward change`
|  type  |     name      |      description       |
|:------:|:-------------:|:----------------------:|
|  int   | typeofrequest | type of request number |
| String |      id       |        user id         |
| String |     email     |       user email       |
| String |     phone     |   user phone number    |


## Create room number

* `roomnumber`: md5(number of members + user_id of members + time)
* https://en.wikipedia.org/wiki/MD5

## Create user_id number
* `user_id`: mysql auto increment
* https://dev.mysql.com/doc/refman/5.6/en/innodb-auto-increment-handling.html