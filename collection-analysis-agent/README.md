# Collection Analysis Agent

This service provides REST endpoints to analyze MongoDB collections and retrieve various statistics and information.

## API Endpoints

### Get Collection Statistics

Retrieve statistics for a specific collection.

```bash
GET /api/v1/collection/{collectionName}/stats
```

**Response:** Returns collection statistics including size, document count, and other metrics.

### Get Collection Schedule

Retrieve scheduling information for a specific collection.

```bash
GET /api/v1/collection/{collectionName}/schedule
```

**Response:** Returns scheduling information for the collection.

### Get Recent Documents

Retrieve the most recent documents from a specific collection.

```bash
GET /api/v1/collection/{collectionName}/recent
```

**Parameters:**
- `collectionName`: Name of the MongoDB collection (path parameter)

**Response:** Returns a list of the 5 most recent documents from the specified collection.

## Example Usage

### Get Collection Stats
```bash
curl -X GET "http://localhost:8082/api/v1/collection/users/stats"
```

### Get Collection Schedule
```bash
curl -X GET "http://localhost:8082/api/v1/collection/users/schedule"
```

### Get Recent Documents
```bash
curl -X GET "http://localhost:8082/api/v1/collection/users/recent"
```

## Response Examples

### Collection Stats Response
```json
{
    "documentCount": 1000,
    "totalSize": 2048576,
    "avgDocumentSize": 2048
}
```

### Collection Schedule Response
```json
{
    "lastUpdated": "2023-12-01T10:00:00Z",
    "nextScheduledUpdate": "2023-12-01T11:00:00Z",
    "updateFrequency": "hourly"
}
```

### Recent Documents Response
```json
[
    {
        "_id": "657b1234c1234567890abcde",
        "name": "John Doe",
        "createdAt": "2023-12-01T10:00:00Z"
    },
    {
        "_id": "657b1234c1234567890abcdf",
        "name": "Jane Smith",
        "createdAt": "2023-12-01T09:55:00Z"
    }
]
```