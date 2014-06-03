//
// Copyright 2010 GOT5 (GO Tapestry 5)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// 	http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package dev.openshift.tapestry.angular;


public class AngularSymbolConstants {

    
    /**
     * Base path for angular. angular-${angular.version}.js is assumed to be in
     * there.
     */
    public static final String ANGULAR_CORE_PATH = "angular.core.path";

    /**
     * The angular version number. Must match the normal angular file name
     * pattern: <code>angular-${angular.version}.js</code> or
     * <code>angular.mobile-${angular.version}.min.js</code>
     */
    public static final String ANGULAR_VERSION = "angular.version";
    
    public static final String ANGULAR_PAGES_SUBPACKAGE = "angular.pages.subpackage";
  
}
