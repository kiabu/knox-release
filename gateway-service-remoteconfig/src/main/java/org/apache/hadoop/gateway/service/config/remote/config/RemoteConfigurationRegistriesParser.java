/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.hadoop.gateway.service.config.remote.config;

import org.apache.hadoop.gateway.service.config.remote.RemoteConfigurationRegistryConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class RemoteConfigurationRegistriesParser {

    static List<RemoteConfigurationRegistryConfig> getConfig(String configFilename) {
        List<RemoteConfigurationRegistryConfig> result = new ArrayList<>();

        File file = new File(configFilename);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RemoteConfigurationRegistries.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            RemoteConfigurationRegistries parsedContent = (RemoteConfigurationRegistries) jaxbUnmarshaller.unmarshal(file);
            if (parsedContent != null) {
                result.addAll(parsedContent.getRegistryConfigurations());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;
    }
}
