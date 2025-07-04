package Practice;

import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.query.Report;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProcessTest implements JavaDelegate {
		private ProcessEngine processEngine;
		@BeforeClass
		public void setUp() {
			processEngine =ProcessEngineConfiguration
					.createStandaloneInMemProcessEngineConfiguration()
					.buildProcessEngine();
			BpmnAwareTests.init(processEngine);
			
			processEngine.getRepositoryService()
					.createDeployment()
					.addClasspathResource("Practice/Test.bpmn")
					.deploy();
		}
		@AfterClass
		public void tearDown() {
			if (processEngine !=null) {
				processEngine.close();
			}
		}
		
		@Test
		@Deployment(resources = "Practice/Test.bpmn")
		public void testprocess() {
			ProcessInstance processInstance=processEngine.getRuntimeService()
					.startProcessInstanceByKey("Process_0h21orh");
			BpmnAwareTests.assertThat(processInstance).isStarted();
			
			completeNextUserTask(processInstance, "saddam");
	        completeNextUserTask(processInstance, "syed");
	        
			
		    BpmnAwareTests.assertThat(processInstance).isEnded();
		}
		 private void completeNextUserTask(ProcessInstance processInstance, String expectedTaskName) {
		        Task task = processEngine.getTaskService()
		                .createTaskQuery()
		                .processInstanceId(processInstance.getId())
		                .singleResult();

		        if (task == null) {
		            System.out.println("No task found for process instance: " + processInstance.getId());
		            
		            List<Task> allTasks = processEngine.getTaskService().createTaskQuery().list();
		            
		            System.out.println("Available tasks:");
		            
		            for (Task t : allTasks) {
		                System.out.println("Task name: " + t.getName() + ", ID: " + t.getId());
		            }
		            throw new AssertionError("Expected task '" + expectedTaskName + "' was not found.");
		        }

		        System.out.println("Found task: " + task.getName());
		        BpmnAwareTests.assertThat(task).hasName(expectedTaskName);
		        processEngine.getTaskService().complete(task.getId());
		    }
		@Override
		public void execute(DelegateExecution execution) throws Exception {
			String activityId = execution.getCurrentActivityId();
		    String activityName = execution.getCurrentActivityName();
		    System.out.println("Service Task: " + activityName + " (ID: " + activityId + ")");
		   // Reporter.log("This is a log message in the report",true);
		}
		
}
					
			