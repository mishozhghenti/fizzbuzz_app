## FizzBuzz Enterprise Version

#### Used technologies:
* Java 1.8
* Spring Framework (Spring Boot, Spring Security)
* Hibernate
* H2 Database
* Maven
* Docker

##### Build project:
    docker-compose build
##### Run project
    docker-compose up
##### By default, application runs on 8080 port. Provided `application.property` for detailed configurations.

## Application Architecture
Required to register a new user into system and after successful authorization user can request a specific controller to get FizzBuzz result as a list in a JSON format. <br/>
The project itself has an independent configuration `rulesConfig.json` file where rules are configured.<br/>
By default, the configuration includes only classical FizzBuzz logic, but it is very easy to extend by adding new JSON structure in it. <br/>
(For example: numbers divisible by 7 should be displayed as "Haha" or etc.)<br/>


## Application API with examples
* #### Sign Up
##### `{host}/api/auth/signup` POST request
Body is in a JSON format:
```
{
	"fullName":"Mikheil Zhghenti",
	"username":"misho.zhghenti",
	"password":"123456",
	"role":"ROLE_ADMIN"
}
```

Roles could be anything (for instance: `ROLE_USER` or etc.), however only `ROLE_ADMIN` is privileged to call `{host}/api/fizzbuzz/evaluation` controller which is discussed further.
* #### Sign In
##### `{host}/api/auth/signin` POST request
```
{
	"username":"misho.zhghenti",
	"password":"123456"
}
```

If it is a successful we would receive a response like this:

```
{
    "roles": [
        "ROLE_ADMIN"
    ],
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaXNoby56aGdoZW50aSIsImlhdCI6MTU5NjcwNTcwMSwiZXhwIjoxNTk2NzkyMTAxfQ.FYh8iRT7rgu6UuQmJl_-L0pGAaXkOvaNnosgiFl98HNDy0FPeREe-PhhryuRnBlOIyC5obhCJP29gQeJd7Wmdg",
    "type": "Bearer",
    "id": 1,
    "fullName": "Mikheil Zhghenti",
    "userName": "misho.zhghenti"
}
```
Where `token` is actually `JWT` token
* #### FizzBuzz Result
Returns FizzBuzz output
##### `{host}/api/fizzbuzz/evaluation` GET request

A Token is passed in Authorization Headers like this:<br/>
```
"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaXNoby56aGdoZW50aSIsImlhdCI6MTU5NjcwNTcwMSwiZXhwIjoxNTk2NzkyMTAxfQ.FYh8iRT7rgu6UuQmJl_-L0pGAaXkOvaNnosgiFl98HNDy0FPeREe-PhhryuRnBlOIyC5obhCJP29gQeJd7Wmdg"
```
If an authorization passed, we would get an output as a list of FizzBuzz logic due to configured rules. <br/>
P.S if several rules are satisfied, only one with the 'highest' rule (a rule with the highest divisible number among the satisfied rules) will be executed. 
[
    1,
    2,
    "Fizz",
    4,
    "Buzz",
    "Fizz",
    7,
    8,
    "Fizz",
    "Buzz",
    11,
    "Fizz",
    13,
    14,
    "FizzBuzz",
    16,
    17,
    "Fizz",
    19,
    "Buzz",
    "Fizz",
    22,
    23,
    "Fizz",
    "Buzz",
    26,
    "Fizz",
    28,
    29,
    "FizzBuzz",
    31,
    32,
    "Fizz",
    34,
    "Buzz",
    "Fizz",
    37,
    38,
    "Fizz",
    "Buzz",
    41,
    "Fizz",
    43,
    44,
    "FizzBuzz",
    46,
    47,
    "Fizz",
    49,
    "Buzz",
    "Fizz",
    52,
    53,
    "Fizz",
    "Buzz",
    56,
    "Fizz",
    58,
    59,
    "FizzBuzz",
    61,
    62,
    "Fizz",
    64,
    "Buzz",
    "Fizz",
    67,
    68,
    "Fizz",
    "Buzz",
    71,
    "Fizz",
    73,
    74,
    "FizzBuzz",
    76,
    77,
    "Fizz",
    79,
    "Buzz",
    "Fizz",
    82,
    83,
    "Fizz",
    "Buzz",
    86,
    "Fizz",
    88,
    89,
    "FizzBuzz",
    91,
    92,
    "Fizz",
    94,
    "Buzz",
    "Fizz",
    97,
    98,
    "Fizz",
    "Buzz"
]
 
 

## Rules Configuration JSON file (`rulesConfig.json`)

```
[
  {
    "rule_id": 1,
    "rule_result": "Fizz",
    "dividable_by": 3
  },
  {
    "rule_id": 2,
    "rule_result": "Buzz",
    "dividable_by": 5
  },
  {
    "rule_id": 3,
    "rule_result": "FizzBuzz",
    "dividable_by": 15
  }
]
```
It is a list of rules where each of it contains ID, output and divisible number. <br/>

In case we want to add a new rule as mentioned above (Divisible by 7 - "Haha" Rule) we would have:
```
[
  {
    "rule_id": 1,
    "rule_result": "Fizz",
    "dividable_by": 3
  },
  {
    "rule_id": 2,
    "rule_result": "Buzz",
    "dividable_by": 5
  },
  {
    "rule_id": 3,
    "rule_result": "FizzBuzz",
    "dividable_by": 15
  },
  {
    "rule_id": 4,
    "rule_result": "Haha",
    "dividable_by": 7
  }
]
```


# From the suggestions achieved:
* No Loops (replaced with Recursion)
* No %, /, *, - ( replaced with the principle as (a-b) == (a + ~b + 1) )
* Only +
* Dockerize the application















