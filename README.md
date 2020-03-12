The BusinessPlan is an abstract class. Check this file first. We have 3 test files. Except the TestVMOSA file, the other two only have one test method.
Since the BusinessPlan has only one abstract class that is different for differnt classes, we put all the tests of the common methods and the test for Section
in the TestVMOSA file but in different methods. The TestBYB and TestCNTRAssessment only have tests for addSection since it is the only different method. 
Althoght I already put comments for each file, there are some points that I want to talk about.
First, the addSection file may be simplified and throw Exceptions depending on future designs. 
Second, we don't have test for setRoot in the BusinessPlan, the method is only for XML conversion. 