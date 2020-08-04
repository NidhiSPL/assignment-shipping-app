# Container Shipping Application

JMS Messaging Flow:

1. JMS Message will be received on the Queue "STATUS.INBOUND.QUEUE"
2. Once message is received check perform the below validation.
	- Valid JSON
	- Is the time stamp of the message is after the most recent in the table for the containerId.
3. If these conditions are failed, then it will be sent to "STATUS.INBOUND.QUEUE.DLQ"
4. If conditions are satisfied, post the message in "STATUS.OUTBOUND.QUEUE"


REST API Doc:
1. Get Containers By OwnerId
	/api/v1/container/{containerOwnerId} -> List<Container>

2.	Add new Container
	/api/v1/container/{containerOwnerId} -> Container
	
3.	Update Container Status
	/api/v1/container/{containerId}?status={containerStatus} -> Container
	
4.	Delete a Container By containerId
	/api/v1/container/{containerId} -> "Container with containerId : {containerId} deleted successfully."
	



