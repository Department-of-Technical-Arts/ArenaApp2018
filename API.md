A list of APIs being used in the app:

base-url = "http://www.bits-arena.com/api/"

For the below,
complete url = base-url + relative-endpoint

#### All Events (Full data)

Type: **GET**  
Relative endpoint: `events/`  
Response: JSONArray with each item being a JSONObject representing one sport

Example: "http://www.bits-arena.com/api/events/"
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
#### All Events (Light version)

Type: **GET**  
Relative endpoint: `events/index/`  
Response: Same as above, but doesn't contain the text heavy `rules` field.

Example: "http://www.bits-arena.com/api/events/index"
```
[
  {
    "name": "Badminton Girls",
    "tagline": "",
    "route": "BadmintonGirls",
    "_id": "5a41d9c9e9587557556a77fb",
    "category": "Girls"
  },
  {
    "name": "Badminton Mixed",
    "tagline": "",
    "route": "BadmintonMixed",
    "_id": "5a41dd51e9587557556a780a",
    "category": "Boys"
  },
  {
    "name": "Carrom ",
    "tagline": "",
    "route": "Carrom",
    "_id": "5a41daa0e9587557556a77fc",
    "category": "Open"
  }
]
```
#### Event by ID

Type: **GET**  
Relative endpoint: `events/id` (i.e. `id` is replaced with an actual id)  
Response: A single JSONObject containting the event's data  

Example: "http://www.bits-arena.com/api/events/5a39e9f972f7a67961ad41a1"
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

#### Arena Scores Leaderboard

Type: **GET**
Relative endpoint: `/scores/leaderboard`

Example: "http://bits-arena.com/api/scores/leaderboard"
```
[
    {
        "_id": "5a49ff33c2d45e765fc357e8",
        "name": "Birla Institute of Technology & Science - Goa",
        "updatedAt": "2018-01-01T09:28:19.353Z",
        "__v": 0,
        "createdAt": "2018-01-01T09:28:19.353Z",
        "others": [],
        "bronze": [],
        "silver": [
            "Cricket"
        ],
        "gold": []
    },
    {
        "_id": "5a49ff58c2d45e765fc35837",
        "name": "Harvard University",
        "updatedAt": "2018-01-01T09:28:56.824Z",
        "__v": 0,
        "createdAt": "2018-01-01T09:28:56.824Z",
        "others": [],
        "bronze": [
            "Cricket"
        ],
        "silver": [],
        "gold": [
            "Tennis",
            "Wolf"
        ]
    },
    {
        "_id": "5a49ff17c2d45e765fc357b3",
        "name": "Birla Institute of Technology & Science - Hyderabad",
        "updatedAt": "2018-01-01T09:27:51.016Z",
        "__v": 0,
        "createdAt": "2018-01-01T09:27:51.016Z",
        "others": [
            "Football"
        ],
        "bronze": [
            "Wolf"
        ],
        "silver": [
            "Tennis",
            "Generic/None"
        ],
        "gold": [
            "Cricket",
            "Contact"
        ]
    }
]
```

#### InterBITS Scores Leaderboard

Type: **GET**
Relative endpoint: `/scores/leaderboard/bits`

Example: "http://bits-arena.com/api/scores/leaderboard/bits"
Response is same as above, but returns only BITS colleges.

#### All newsletter articles

Type: **GET**
Relative endpoint: `news/index`

Example: "http://bits-arena.com/api/news/index"
```
[
    {
        "_id": "5a55cb6c1cbbe128b6d9eadd",
        "title": "random",
        "text": "(redacted)",
        "keywords": [],
        "authors": []
    },
    {
        "_id": "5a55cba51cbbe128b6d9eade",
        "title": "random 2",
        "text": "(redacted)",
        "keywords": [],
        "authors": []
    },
    {
        "_id": "5a55ce208a1ca6fc7e80e2c2",
        "title": "lorem ipsum but html generator",
        "text": "(redacted)",
        "keywords": [],
        "authors": []
    },
    {
        "_id": "5a55cfa58a1ca6fc7e80e2c3",
        "title": "Pure HTML, Multiple elements. Should be interesting. Also, long heading. Have fun.",
        "text": "(redacted)",
        "keywords": [],
        "authors": []
    }
]
```

#### Single newsletter article

Type: **GET**
Relative endpoint: `news/id` (`id` is a var)

Example: "http://bits-arena.com/api/news/5a55cfa58a1ca6fc7e80e2c3"
```
{
    "_id": "5a55cfa58a1ca6fc7e80e2c3",
    "title": "Pure HTML, Multiple elements. Should be interesting. Also, long heading. Have fun.",
    "text": "Article text here",
    "keywords": [],
    "authors": []
}
```

#### News Feed

Type: **GET**
Relative endpoint: `/scores/feed`

Example: "http://bits-arena.com/api/scores/feed"
```
[
    {
        "_id": "5a4a2ae0b8246742341ae927",
        "updatedAt": "2018-01-01T12:34:40.961Z",
        "createdAt": "2018-01-01T12:34:40.961Z",
        "sport": "Cricket",
        "text": "YJ scored a century. Yes, a century in a 10 over match. Dafuq he's doing.",
        "team1": "Birla Institute of Technology & Science - Hyderabad",
        "team2": "Birla Institute of Technology and Science, Pilani",
        "__v": 0
    },
    {
        "_id": "5a4a2b12b8246742341ae928",
        "updatedAt": "2018-01-01T12:35:30.296Z",
        "createdAt": "2018-01-01T12:35:30.296Z",
        "sport": "Tennis",
        "text": "Floof started playing tennis. And represents some unknown college.",
        "team1": "Marathwada Mitra Mandals College of Engineering",
        "team2": "",
        "__v": 0
    },
    {
        "_id": "5a4a2b3bb8246742341ae929",
        "updatedAt": "2018-01-01T12:36:11.227Z",
        "createdAt": "2018-01-01T12:36:11.227Z",
        "sport": "Football",
        "text": "All matches delayed as the refree died from free redbulls.",
        "team1": "",
        "team2": "",
        "__v": 0
    }
]
```

