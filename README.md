# find_min_example

## The following **find_min** function should return the smallest integer from a given array A.

```java
int find_min(int[] A) {
    int ans = 0;
    for (int i = 1; i &lt; A.length; i++) {
        if (ans &gt; A[i]) {
            ans = A[i];
        }
    }
    return ans;
}
```

Unfortunately it is an incorrect implementation. In other words, when the function is called with certain parameters, it returns the wrong answer. Your task is to generate a counterexample, i.e. an array A consisting of N integers such that the **find_min** function returns the wrong answer.
Write a function:
```javaclass Solution { public int[] solution(int N); }```
that, given an integer N, returns an array A consisting of N integers which describes a counterexample.
## Example:
Given N = 4, your function may return [4, 2, 4, 5]. It is a counterexample, because calling the **find_min** function with this array returns 0, but the correct answer is 2. Your function may also return another counterexample; for example, [10, 567, 99, 456
Assume that:
N is an integer within the range [1..1,000].

# Manage Tasks

## You're working on a system that helps to manage tasks on a todo list.
Your job is to implement an endpoint that allows these tasks to be updated.
In order to achieve this, you should use the Hibernate and Spring frameworks.

### Application
You are given a Spring Boot application (version 2.0.5) with spring-boot-starter-data-jpa and spring-boot-starter-web modules included. Database access is fully configured. The application is compiled using JDK 8.

### Database
Tasks are stored in a relational database. The DB schema looks like this:
```sql
CREATE TABLE task (
  id bigint NOT NULL,
  description varchar(200) NOT NULL,
  priority bigint,
  PRIMARY KEY (id)
);
```

### Endpoint
Sending a request:
```javascript PUT /tasks/{id} ```
with request body:

```json
{
   "description": "task's description"
   "priority": 5
}
```

should update the description and priority of the task with id = ID. Changes should be stored in the database.
### Your tasks

..1. Configure the Task class as a Hibernate entity<
..1. Implement an endpoint as described above. Furthermore, it should meet the following requirements:

... Endpoint should return error code 404 if a task with the given id does not exist. In response, the server should return the following JSON:
```json    
	{
       "message": "Cannot find task with given id"
       "status": 404
    }
```

... Endpoint should return error code 400 if the task description is null (or key "description" is not present in the request JSON). In response, the server should return the following JSON:
```json
    {
       "message": "Task description is required"
       "status": 400
    }
```

... Endpoint should return error code 200 when the task is successfully updated. The response body should be the same as the request body, for example:
```json
	{
		"description": "task's description"
		"priority": 5
	}
```

### Remark
You can place more than one class declaration in the editor. Please remember not to use public classes.

