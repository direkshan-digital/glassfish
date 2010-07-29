/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2005-2010 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 *
 * Contributor(s):
 *
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.sun.appserv.test.util.results;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class holding One Test info.
 *
 * @Author : Ramesh Mandava
 * @Last Modified :Initial creation By Ramesh on 10/24/2001
 * @Last Modified : By Ramesh on 1/20/2002 , For preserving order of entry of testcases used a separate
 * testCaseIdVector
 * @Last Modified : By Justin Lee on 10/05/2009
 */
public class Test {
    private String id;
    private String name = ReporterConstants.NA;
    private String description = ReporterConstants.NA;
    private String status = ReporterConstants.OPTIONAL;
    private String statusDescription = ReporterConstants.OPTIONAL;
    private String expected;
    private String actual;
    private List<TestCase> testCases = new ArrayList<TestCase>();

    public Test() {
    }

    public Test(String id) {
        this.id = SimpleReporterAdapter.checkNA(id);
    }

    public Test(String id, String name) {
        this(id);
        this.name = SimpleReporterAdapter.checkNA(name);
    }

    public Test(String id, String name, String description) {
        this(id, name);
        this.description = SimpleReporterAdapter.checkNA(description);
    }

    public void setStatus(String status) {
        this.status = SimpleReporterAdapter.checkNA(status);
    }

    public void setStatusDescription(String desc) {
        statusDescription = SimpleReporterAdapter.checkNA(desc);
        expected = null;
        actual = null;
    }

    public void setExpected(String expected) {
        this.expected = SimpleReporterAdapter.checkNA(expected);
    }

    public void setActual(String actual) {
        this.actual = SimpleReporterAdapter.checkNA(actual);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public String getExpected() {
        return expected;
    }

    public String getActual() {
        return actual;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Test");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", actual='").append(actual).append('\'');
        sb.append(", expected='").append(expected).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", statusDescription='").append(statusDescription).append('\'');
        sb.append(", testCases='").append(testCases).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String toXml() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<test>\n");
        buffer.append("<id>" + id + "</id>\n");
        if (!name.equals(ReporterConstants.NA)) {
            buffer.append("<name>" + name.trim() + "</name>\n");
        }
        if (!description.equals(ReporterConstants.NA)) {
            buffer.append("<description><![CDATA[" + description.trim() + "]]></description>\n");
        }
        if (!status.equals(ReporterConstants.OPTIONAL)) {
            buffer.append("<status value=\"" + status + "\">");
            if (!description.equals(ReporterConstants.OPTIONAL)) {
                buffer.append("<![CDATA[" + description.trim() + "]]>");
            } else if (expected != null && actual != null) {
                buffer.append("<expected><![CDATA[" + expected.trim() + "]]></expected>"
                    + "<actual><![CDATA[" + actual.trim() + "]]></actual>");
            }
            buffer.append("</status>\n");
        }
        if (!testCases.isEmpty()) {
            buffer.append("<testcases>\n");
            for (TestCase myTestCase : testCases) {
                buffer.append(myTestCase.toXml());
            }
            buffer.append("</testcases>\n");
        }
        buffer.append("</test>\n");
        return buffer.toString();
    }

}
