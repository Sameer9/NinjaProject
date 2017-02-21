/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;


import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        
    
        router.GET().route("/").with(ApplicationController::index);
        router.GET().route("/hello_world.json").with(ApplicationController::helloWorldJson);
//        router.GET().route("/PrintingMyName").with(ApplicationController::MyName);
        router.GET().route("/PrintingAllBooks").with(ApplicationController::PrintingBook);
        router.GET().route("/UI").with(ApplicationController::main);
        router.POST().route("/DeleteBooks").with(ApplicationController::deleteBook);
//        trying printing my name
        router.POST().route("/EditBooks").with(ApplicationController::editBook);
        router.POST().route("/AddBooks").with(ApplicationController::newBook);
//        router.GET().route("/").with(ApplicationController::myName);
        
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(ApplicationController::index);
    }

}
