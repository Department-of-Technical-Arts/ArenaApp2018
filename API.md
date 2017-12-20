A list of APIs being used in the app:

base-url = "http://www.bits-arena/api/events"

For the below,
complete url = base-url + relative-endpoint

#### All Events

Type: **GET**  
Relative endpoint: `/`  
Response: JSONArray with each item being a JSONObject representing one sport

Example: "http://www.bits-arena/api/events/"
```
[
    {
        "_id": "5a39e9f972f7a67961ad41a1",
        "updatedAt": "2017-12-20T08:03:27.788Z",
        "createdAt": "2017-12-20T04:41:29.545Z",
        "body": "5a39e9c5b44e6bf06e06b7dc",
        "about": "",
        "name": "Football",
        "tagline": "",
        "category": "",
        "startTime": "",
        "endTime": "",
        "type": "",
        "venue": "",
        "route": "football",
        "prize": null,
        "price": 0,
        "teamSize": 11,
        "__v": 0,
        "users": [],
        "teams": [
            "829BGH8"
        ]
    }
]
```

Tags we need:
* `about` contains the textual description, including contacts
* `name`
* `startTime` and `endTime`
* `venue`
* `prize`

#### Event by ID

Type: **GET**  
Relative endpoint: `/id` (i.e. `id` is replaced with an actual id)  
Response: A single JSONObject containting the event's data  

Example: "http://www.bits-arena/api/events/5a39e9f972f7a67961ad41a1"
```
{
    "_id": "5a39e9f972f7a67961ad41a1",
    "updatedAt": "2017-12-20T08:03:27.788Z",
    "createdAt": "2017-12-20T04:41:29.545Z",
    "body": "5a39e9c5b44e6bf06e06b7dc",
    "about": "",
    "name": "Football",
    "tagline": "",
    "category": "",
    "startTime": "",
    "endTime": "",
    "type": "",
    "venue": "",
    "route": "football",
    "prize": null,
    "price": 0,
    "teamSize": 11,
    "__v": 0,
    "users": [],
    "teams": [
        "829BGH8"
    ]
}
```
