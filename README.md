
# Getting Started
Sample application using full-text search with filters on different indexes based on an Instagram-like database.


## How to start
- create `.env` file in the same fold with docker-compose:
- Run **`docker-compose.yml`** for elasticsearch/kibana:
If you have problem with mem-limit, you can solve it using command `sudo sysctl -w vm.max_map_count=262144 `
- to create a mapping of indexes and fill them with data, you can use the file **`script.elastic`**

- enter the **elasticsearch.username** and **elasticsearch.password** in `application.proeprties`
- **run** application

## Client
- For interaction after starting the application, you can look at the api through the swagger with url `http://localhost:8080/swagger-ui/index.html`



