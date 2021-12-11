/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cloud.bindings.boot.Guard;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vitaliy Fedoriv
 *
 */

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/envvarchecks")
public class EnvVarCheckRestController {

    @RequestMapping(value = "/dummy", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> Dummy() {

        // Map<String, String> env = System.getenv();
        // for (String envName : env.keySet()) {
        // System.out.println(String.format("%s : %s", envName, env.get(envName)));
        // }
        HashMap<String, String> map = new HashMap<>();
        System.out.println(System.getenv("PING_WEBSITE"));
        map.put("key", "value");
        map.put("foo", "bar");
        map.put("aa", "bb");
        return map;
    }

    @RequestMapping(value = "/dummyset", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> Dummyset() {
        System.out.println("Going in.....\n\n");
        ProcessBuilder processBuilder = new ProcessBuilder();
        Map<String, String> env = processBuilder.environment();
        env.put("PING_WEBSITE", "stackabuse.com");

        if (System.getProperty("os.name").startsWith("Windows")) {
            processBuilder.command("cmd.exe", "/c", "ping -n 3 %PING_WEBSITE%");
        } else {
            processBuilder.command("/bin/bash", "-c", "ping $PING_WEBSITE$");
        }

        try {
            // Starting the process...
            Process process = processBuilder.start();

            // Reading the output of the process
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Catch the exit code of our process
            int ret = process.waitFor();

            System.out.printf("Program exited with code: %d", ret);

        } catch (Exception e) {
            // Handle exception...
            e.printStackTrace();
        }
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("key", "value");
        return map1;
    }

    @RequestMapping(value = "/systestput", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> Systestput() {
        System.setProperty("foo", "bar");
        HashMap<String, String> map = new HashMap<>();
        map.put("foo", "bar");
        return map;
    }

    @Autowired
    private Environment env;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public HashMap<String, String> EnvCheckVars() {

        String foo1 = env.getProperty("spring.profiles.active", String.class, "not set");
        System.out.println("org.springframework.cloud.bindings.boot.enable = " + foo1);
        String foo2 = env.getProperty("org.springframework.cloud.bindings.boot.enable", Boolean.class, false)
                .toString();
        System.out.println("org.springframework.cloud.bindings.boot.enable = " + foo2);

        // List<HashMap<String, Boolean>> list = new ArrayList()<>();
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("spring", foo1);
        map1.put("org.springframework.cloud.bindings.boot.enable", foo2);
        return map1;
    }

    @RequestMapping(value = "/cloudbootenable", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Boolean> CloudBootEnable() {

        boolean foo = env.getProperty("org.springframework.cloud.bindings.boot.enable", Boolean.class, false);
        System.out.println("org.springframework.cloud.bindings.boot.enable = " + foo);

        HashMap<String, Boolean> map = new HashMap<>();
        map.put("org.springframework.cloud.bindings.boot.enable", foo);
        return map;
    }

    @RequestMapping(value = "/springprofilesactive", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> SpringProfilesActive() {

        String foo = env.getProperty("spring.profiles.active", String.class, "not set");
        System.out.println("spring.profiles.active = " + foo);

        HashMap<String, String> map = new HashMap<>();
        map.put("org.springframework.cloud.bindings.boot.enable", foo);
        return map;
    }
}
