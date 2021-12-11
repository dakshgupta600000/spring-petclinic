package org.springframework.samples.petclinic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.samples.petclinic.PetClinicApplication;

@SpringBootApplication
public class PetClinicApplicationDemo extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("Going in.....\n\n");
		ProcessBuilder processBuilder = new ProcessBuilder();
		Map<String, String> env = processBuilder.environment();
		env.put("PING_WEBSITE", "stackabuse.com");

		// if (System.getProperty("os.name").startsWith("Windows")) {
		// processBuilder.command("cmd.exe", "/c", "ping -n 3 %PING_WEBSITE%");
		// } else {
		// processBuilder.command("/bin/bash", "-c", "ping $PING_WEBSITE$");
		// }

		try {
			// Starting the process...
			// Process process = processBuilder.start();
			// ProcessBuilder pb = new ProcessBuilder(
			// "\'C:\\Program Files\\Java\\jdk-17.0.1\\bin\\java.exe\'
			// \'-XX:+ShowCodeDetailsInExceptionMessages\'
			// \'@C:\\Users\\AVV6D9~1\\AppData\\Local\\Temp\\cp_61s5esrecop4xibt9h8xzl3ws.argfile\'
			// \'org.springframework.samples.petclinic.PetClinicApplication1\'");
			// process = pb.start();

			String[] command = {
					"c:\\Users\\AVV6D9744\\Desktop\\New folder\\spring-petclinic-rest-master (2)\\spring-petclinic-rest-master",
					"; &",
					"C:\\Program Files\\Java\\jdk-17.0.1\\bin\\java.exe",
					// "\'-XX:+ShowCodeDetailsInExceptionMessages\'",
					// "\'@C:\\Users\\AVV6D9~1\\AppData\\Local\\Temp\\cp_61s5esrecop4xibt9h8xzl3ws.argfile\'",
					"\'org.springframework.samples.petclinic.PetClinicApplication1\'" };
			// String[] command = { "cmd", "/C", "echo FOO: %FOO%" };
			String[] envp = { "FOO=false" };

			Process p = Runtime.getRuntime().exec(command, envp);
			BufferedReader reader1 = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s = reader1.readLine();
			System.err.println(s);

			// Reading the output of the process
			// try (BufferedReader reader = new BufferedReader(
			// new InputStreamReader(process.getInputStream()))) {

			// String line;

			// while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			// }
			// }

			// // Catch the exit code of our process
			// int ret = process.waitFor();

			// System.out.printf("Program exited with code: %d", ret);

		} catch (Exception e) {
			// Handle exception...
			e.printStackTrace();
		}

		// SpringApplication.run(PetClinicApplication.class, args);
	}
}
