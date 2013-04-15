/*
 * Copyright 2002-2012 SCOOP Software GmbH
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
package de.scoopgmbh.copper.monitor.server;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.util.Date;

import de.scoopgmbh.copper.monitor.adapter.model.SystemResourcesInfo;

public class PerformanceMonitor { 

    private double boundValue(double value){
    	return Math.max(value, 0);
    }
    
    //http://docs.oracle.com/javase/7/docs/jre/api/management/extension/com/sun/management/OperatingSystemMXBean.html
    public SystemResourcesInfo getRessourcenInfo(){
    	OperatingSystemMXBean operatingSystemMXBean= ManagementFactory.getOperatingSystemMXBean();
    	MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
    	java.lang.management.ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    	ClassLoadingMXBean classLoadingMXBean  = ManagementFactory.getClassLoadingMXBean();
    	return new SystemResourcesInfo(new Date(),
    			boundValue(0),
    			0,
    			boundValue(0),
    			memoryMXBean.getHeapMemoryUsage().getUsed(),
    			threadMXBean.getThreadCount(),
    			classLoadingMXBean.getTotalLoadedClassCount());
    }

}