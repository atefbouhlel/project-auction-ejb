This helloworld example is extracted and adapted from
https://github.com/javaee/glassfish-samples/tree/master/ws/javaee7/ejb/hello-stateless-ejb

To compile and install, execute the following command:
$ mvn clean install

The EJB bean is deployed with the Embedded Glassfish Server using
Maven and this is done in the JUnit test of the module
entity-bean-example-bean. For some explanations on the usage of
Embedded Glassfish in JUnit tests, see for instance
https://docs.oracle.com/javaee/7/tutorial/ejb-embedded003.htm.

Afterwards, the EJB bean can be deployed with the Glassfish asadmin tool.
The EJB bean is deployed with the Glassfish asadmin tool.
Before executing the example, check your configuration:
$ asadmin help

To run the example, execute the following commands:
$ asadmin start-domain domain1; asadmin start-database; asadmin deploy project-auction-bean/target/entity-bean.jar

$ (cd project-auction-client/; java -classpath $CLASSPATH:../project-auction-bean/target/entity-bean.jar:target/project-auction-client-4.0-SNAPSHOT.jar entreprise/entity_bean_client/StatelessJavaClient)

Undeploy the component and stop the container by executing the commands:
$ asadmin undeploy entity-bean; asadmin stop-database; asadmin stop-domain domain1

Known Bug:
----------
The command mvn exec:java to execute the client in the module
project-auction-client does not work:
...
Inserting Customer and Orders... OK
Verifying that all are inserted... OK
javax.ejb.EJBException: java.rmi.MarshalException: CORBA MARSHAL 1330446347 Maybe; nested exception is: 
	org.omg.CORBA.MARSHAL: WARNING: 00810011: Exception from readValue on ValueHandler in CDRInputStream  vmcid: OMG  minor code: 11 completed: Maybe
	at entreprise.entity_bean_api._StatelessSession_Wrapper.findCustomer(entreprise/entity_bean_api/_StatelessSession_Wrapper.java)
	at StatelessJavaClient.main(StatelessJavaClient.java:21)
