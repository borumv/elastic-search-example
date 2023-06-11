
# Getting Started
Sample application using full-text search on different indexes based on an Instagram-like database.


## How to start
- create `.env` file in the same fold with docker-compose:
- Run **`docker-compose.yml`** for elasticsearch/kibana:
If you have problem with mem-limit, you can solve it using command `sudo sysctl -w vm.max_map_count=262144 `
- To create index mappings, as well as to fill in test data, use the following **commands**:
<br>
*Indexes*:
```mongodb-json-query
//Create indexes
PUT /post-index
{
  "settings": {
    "analysis": {
      "analyzer": {
        "my_analyzer": {
          "tokenizer": "standard",
          "filter": ["lowercase"]
        }
      }
    }
  },
  "mappings": {
    "dynamic": "strict",
    "properties": {
      "post_id": {"type": "integer"},
      "author_id": {"type": "integer"},
      "author_nickname": {"type": "keyword"},
      "description": {"type": "text"},
      "post_tags": {
        "type": "text",
        "fields": {
          "keyword": {
            "type": "keyword",
            "ignore_above": 256
          }
        }
      },
      "created_at": {
        "type": "date"
      }
    }
  }
}
PUT /comment-index
{
  "settings": {
    "analysis": {
      "analyzer": {
        "my_analyzer": {
          "tokenizer": "standard",
          "filter": ["lowercase"]
        }
      }
    }
  },
  "mappings": {
    "dynamic": "strict",
    "properties": {
      "comment_id": {"type": "integer"},
      "post_id": {"type": "integer"},
      "author_id": {"type": "integer"},
      "author_nickname": {"type": "keyword"},
      "description": {"type": "text"},
      "created_at": {
        "type": "date"
      }
    }
  }
}
PUT /users-index
{
  "settings": {
     "analysis": {
          "analyzer": {
           "my_analyzer": {
              "tokenizer": "standard",
              "filter": [
                "lowercase"
              ]
            }
          }
        }
      },
      "mappings": {
        "dynamic": "strict",
        "properties": {
          "user_id": {
            "type": "integer"
          },
          "user_nickname": {
            "type": "text"
          },
          "user_firstname": {
            "type": "text"
          },
          "user_lastname": {
            "type": "text"
          },
          "user_bio": {
            "type": "text"
          }
        }
      }
  }
```
*Tests-data*:
```mongodb-json-query
POST /post-index/_bulk
{"index": {"_id": "1"}}
{"post_id": 1, "author_id": 1, "author_nickname": "JohnDoe", "description": "This is the first post about travel.", "post_tags": ["travel", "adventure"], "created_at": "2023-06-01T09:00:00"}
{"index": {"_id": "2"}}
{"post_id": 2, "author_id": 2, "author_nickname": "JaneSmith", "description": "Check out this amazing recipe for chocolate cake!", "post_tags": ["food", "recipe"], "created_at": "2023-06-02T14:30:00"}
{"index": {"_id": "3"}}
{"post_id": 3, "author_id": 1, "author_nickname": "JohnDoe", "description": "Exploring the beautiful beaches of Hawaii.", "post_tags": ["travel", "beach"], "created_at": "2023-06-03T11:15:00"}
{"index": {"_id": "4"}}
{"post_id": 4, "author_id": 3, "author_nickname": "SarahBrown", "description": "A hike through the Rocky Mountains.", "post_tags": ["travel", "nature"], "created_at": "2023-06-04T13:45:00"}
{"index": {"_id": "5"}}
{"post_id": 5, "author_id": 2, "author_nickname": "JaneSmith", "description": "Delicious BBQ recipes for summer gatherings.", "post_tags": ["food", "recipe", "summer"], "created_at": "2023-06-05T16:20:00"}
{"index": {"_id": "6"}}
{"post_id": 6, "author_id": 4, "author_nickname": "MarkJohnson", "description": "Exploring the historic landmarks of Europe.", "post_tags": ["travel", "history"], "created_at": "2023-06-06T11:30:00"}
{"index": {"_id": "7"}}
{"post_id": 7, "author_id": 5, "author_nickname": "EmilyWilson", "description": "Tips and tricks for successful gardening.", "post_tags": ["gardening", "tips"], "created_at": "2023-06-07T09:50:00"}
{"index": {"_id": "8"}}
{"post_id": 8, "author_id": 1, "author_nickname": "JohnDoe", "description": "Visiting the ancient temples of Asia.", "post_tags": ["travel", "culture"], "created_at": "2023-06-08T14:10:00"}
{"index": {"_id": "9"}}
{"post_id": 9, "author_id": 3, "author_nickname": "SarahBrown", "description": "The beauty of autumn colors in the forest.", "post_tags": ["nature", "autumn"], "created_at": "2023-06-09T12:05:00"}
{"index": {"_id": "10"}}
{"post_id": 10, "author_id": 2, "author_nickname": "JaneSmith", "description": "Quick and easy dinner recipes for busy weekdays.", "post_tags": ["food", "recipe", "quick"], "created_at": "2023-06-10T18:30:00"}

POST /comment-index/_bulk
{"index": {"_id": "1"}}
{"comment_id": 1, "post_id": 1, "author_id": 3, "author_nickname": "SarahBrown", "description": "Great post! I love traveling too.", "created_at": "2023-06-01T09:10:00"}
{"index": {"_id": "2"}}
{"comment_id": 2, "post_id": 1, "author_id": 4, "author_nickname": "MarkJohnson", "description": "Thanks for sharing your travel experiences.", "created_at": "2023-06-01T10:20:00"}
{"index": {"_id": "3"}}
{"comment_id": 3, "post_id": 2, "author_id": 5, "author_nickname": "EmilyWilson", "description": "I tried your chocolate cake recipe and it turned out amazing!", "created_at": "2023-06-02T15:00:00"}
{"index": {"_id": "4"}}
{"comment_id": 4, "post_id": 2, "author_id": 3, "author_nickname": "SarahBrown", "description": "I can't wait to try your chocolate cake recipe!", "created_at": "2023-06-02T16:40:00"}
{"index": {"_id": "5"}}
{"comment_id": 5, "post_id": 3, "author_id": 1, "author_nickname": "JohnDoe", "description": "Hawaii is definitely on my travel bucket list.", "created_at": "2023-06-03T16:55:00"}
{"index": {"_id": "6"}}
{"comment_id": 6, "post_id": 4, "author_id": 2, "author_nickname": "JaneSmith", "description": "The Rocky Mountains offer breathtaking views!", "created_at":"2023-06-04T14:05:00"}
{"index": {"_id": "7"}}
{"comment_id": 7, "post_id": 5, "author_id": 4, "author_nickname": "MarkJohnson", "description": "I'm excited to try these BBQ recipes!", "created_at": "2023-06-05T18:50:00"}
{"index": {"_id": "8"}}
{"comment_id": 8, "post_id": 6, "author_id": 5, "author_nickname": "EmilyWilson", "description": "Europe has so much history to explore.", "created_at": "2023-06-06T13:20:00"}
{"index": {"_id": "9"}}
{"comment_id": 9, "post_id": 7, "author_id": 1, "author_nickname": "JohnDoe", "description": "Great tips for successful gardening!", "created_at": "2023-06-07T11:45:00"}
{"index": {"_id": "10"}}
{"comment_id": 10, "post_id": 8, "author_id": 3, "author_nickname": "SarahBrown", "description": "I love learning about different cultures.", "created_at": "2023-06-08T15:55:00"}

POST /users-index/_bulk
{"index": {"_id": "1"}}
{"user_id": 1, "user_nickname": "JohnDoe", "user_firstname": "John", "user_lastname": "Doe", "user_bio": "Travel enthusiast and nature lover."}
{"index": {"_id": "2"}}
{"user_id": 2, "user_nickname": "JaneSmith", "user_firstname": "Jane", "user_lastname": "Smith", "user_bio": "Foodie and recipe creator."}
{"index": {"_id": "3"}}
{"user_id": 3, "user_nickname": "SarahBrown", "user_firstname": "Sarah", "user_lastname": "Brown", "user_bio": "Passionate about photography and adventure."}
{"index": {"_id": "4"}}
{"user_id": 4, "user_nickname": "MarkJohnson", "user_firstname": "Mark", "user_lastname": "Johnson", "user_bio": "Traveling and discovering new cuisines."}
{"index": {"_id": "5"}}
{"user_id": 5, "user_nickname": "EmilyWilson", "user_firstname": "Emily", "user_lastname": "Wilson", "user_bio": "Nature lover and avid gardener."}
{"index": {"_id": "6"}}
{"user_id": 6, "user_nickname": "AlexDavis", "user_firstname": "Alex", "user_lastname": "Davis", "user_bio": "Fitness enthusiast and health advocate."}
{"index": {"_id": "7"}}
{"user_id": 7, "user_nickname": "LisaTurner", "user_firstname": "Lisa", "user_lastname": "Turner", "user_bio": "Art lover and aspiring painter."}
{"index": {"_id": "8"}}
{"user_id": 8, "user_nickname": "MikeRoberts", "user_firstname": "Mike", "user_lastname": "Roberts", "user_bio": "Tech geek and coding enthusiast."}
{"index": {"_id": "9"}}
{"user_id": 9, "user_nickname": "JuliaBaker", "user_firstname": "Julia", "user_lastname": "Baker", "user_bio": "Bookworm and literature lover."}
{"index": {"_id": "10"}}
{"user_id": 10, "user_nickname": "SamAdams", "user_firstname": "Sam", "user_lastname": "Adams", "user_bio": "Musician and songwriter."}
```
- enter the **elasticsearch.username** and **elasticsearch.password** in `application.proeprties`
- **run** application

## Client
- For interaction after starting the application, you can look at the api through the swagger with url `http://localhost:8080/swagger-ui/index.html`



