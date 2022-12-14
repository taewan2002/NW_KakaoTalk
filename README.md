# KakaoTalk

## Basic information
* description: Gachon Univ. 21, Network Term Project - KakaoTalk Clone
* https://www.youtube.com/watch?v=vGXFO11I8CE
* author: 조태완, 김의진, 구승현, 이현우
* version: 1.0.0
* university: Gachon University
* department: Artificial Intelligence
* email: taewan2002@gachon.ac.kr, whitekuj02@gachon.ac.kr, zhdkf2002@gachon.ac.kr, wndo020209@gachon.ac.kr
* last update: 2022-12-14

## protocol
* use protocol.java file
* Serializable interface is used

### `Type 1: make chatting room`
* Send to server as chatting_client.java file
* Room name must be unique
* (room in number + members + made time) is hashed by md5 hash

|        type        |     name      |       description        |
|:------------------:|:-------------:|:------------------------:|
|        int         | typeofrequest |  type of request number  |
|       String       |    sender     | user_id of requst member |
| ArrayList\<String> |     list      |   invite chat members    |


### `Type 2 : invite chatting room`
* Send to server as chatting_client.java file

|        type        |     name      |        description        |
|:------------------:|:-------------:|:-------------------------:|
|        int         | typeofrequest |  type of request number   |
|       String       |    sender     | user_id of requst member  |
|       String       |  roomnumber   | chat room number with md5 |
| ArrayList\<String> |     list      |    invite chat members    |

### `Type 3 : out chatting room`
* Send to server as chatting_client.java file

|  type  |     name      |          description          |
|:------:|:-------------:|:-----------------------------:|
|  int   | typeofrequest |    type of request number     |
| String |    sender     |   user_id of requst member    |
| String |  roomnumber   |  chat room number with md5    |

### `Type 4 : send message`
* Send to server as chatting_client.java file
* every message is cached in server
* message is sent to user when user is online
* message is just cached when user is offline

|  type   |     name      |                description                |
|:-------:|:-------------:|:-----------------------------------------:|
|   int   | typeofrequest |          type of request number           |
| String  |  roomnumber   |         chat room number with md5         |
| String  |    sender     |         user_id of requst member          |
| String  |    message    |              message content              |
| String  |     time      |          time when send messege           |
| boolean |  file_exist   |        Determine if a file exists         |
| String  |   file_path   | Storage Location of sended file in server |

### `Type 5 : logout`
* Send to server as chatting_client.java file
* 
|  type  |     name      |       description        |
|:------:|:-------------:|:------------------------:|
|  int   | typeofrequest |  type of request number  |
| String |    sender     | user_id of requst member |

### `Type 6 : my room list`
* Send to server as get_data.java file
* get room list of user

|  type  |     name      |       description        |
|:------:|:-------------:|:------------------------:|
|  int   | typeofrequest |  type of request number  |
| String |    sender     | user_id of requst member |

### `Type 7 : user list in room`
* Send to server as get_data.java file

|  type  |     name      |          description          |
|:------:|:-------------:|:-----------------------------:|
|  int   | typeofrequest |    type of request number     |
| String |    sender     |   user_id of requst member    |
| String |  roomnumber   |  chat room number with md5    |

### `Type 8 : find ID`
* Send to server as get_data.java file

|  type  |     name      |      description       |
|:------:|:-------------:|:----------------------:|
|  int   | typeofrequest | type of request number |
| String |     name      |     user name info     |
| String |     phone     |   user phone number    |

### `Type 9 : confirm for passward change`
* Send to server as get_data.java file

|  type  |     name      |      description       |
|:------:|:-------------:|:----------------------:|
|  int   | typeofrequest | type of request number |
| String |      id       |        user id         |
| String |     email     |       user email       |
| String |     phone     |   user phone number    |

### `Type 10 : change passward`
* Send to server as get_data.java file

|  type  |     name      |      description       |
|:------:|:-------------:|:----------------------:|
|  type  | typeofrequest | type of request number |
| String |      id       |        user id         |
| String |   password    |   new user password    |

### `Type 16 : add friend`
* Send to server as chatting_client.java file

|        type        |     name      |       description        |
|:------------------:|:-------------:|:------------------------:|
|        int         | typeofrequest |  type of request number  |
|       String       |    sender     | user_id of requst member |
| ArrayList\<String> |     list      |     add friend list      |

### `Type 50: check online user list`
* Send to server as get_data.java file

|        type        |     name      |       description        |
|:------------------:|:-------------:|:------------------------:|
|        int         | typeofrequest |  type of request number  |
|       String       |    sender     | user_id of requst member |
| ArrayList\<String> |     list      |     online user list     |

### `Type 51 : request my informaton`
* Send to server as get_data.java file

|  type  |     name      |       description        |
|:------:|:-------------:|:------------------------:|
|  int   | typeofrequest |  type of request number  |
| String |    sender     | user_id of requst member |

### `Type 53 : delete friend`
* Send to server as get_data.java file

|  type  |     name      |       description        |
|:------:|:-------------:|:------------------------:|
|  int   | typeofrequest |  type of request number  |
| String |    sender     | user_id of requst member |
| String |    friend     | user_id of friend member |

### `Type 54 : request friend list`
* Send to server as get_data.java file

|  type  |     name      |       description        |
|:------:|:-------------:|:------------------------:|
|  int   | typeofrequest |  type of request number  |
| String |    sender     | user_id of requst member |

### `Type 55 : secession`
* Send to server as get_data.java file

|  type  |     name      |       description        |
|:------:|:-------------:|:------------------------:|
|  int   | typeofrequest |  type of request number  |
| String |    sender     | user_id of requst member |


## Chatting structure
* Chatting room is created when two or more users are chatting
1. user send message to server type 4
2. sever send message to all user in room type 4 and cache message in server
3. user receive message from server type 4 and show message in chat window
- if user is offline, message is cached in server
- if user is online, message is shown in chat window
- if user login, get cached message from server and show message in chat window
- if user logout, cached message is deleted in client


## Create room number
* `roomnumber`: md5(number of members + user_id of members + time)
* https://en.wikipedia.org/wiki/MD5

## Create user_id number in server
* `user_id`: mysql auto increment
* https://dev.mysql.com/doc/refman/5.6/en/innodb-auto-increment-handling.html


## Reference
* https://github.com/taewan2002/DB_instagram_server
* https://github.com/taewan2002/DB_instagram
* https://m.blog.naver.com/war2i7i7/220796880477
