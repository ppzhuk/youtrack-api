/**
 * The MIT License
 * <p>
 * Copyright (c) 2013, Sebastian Sdorra
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package sonia.youtrack;

//~--- non-JDK imports --------------------------------------------------------

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import sonia.youtrack.dto.IssueDTO;
import sonia.youtrack.internal.Strings;
import sonia.youtrack.internal.URLs;

import javax.ws.rs.core.MultivaluedMap;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//~--- JDK imports ------------------------------------------------------------

/**
 *
 * @author Sebastian Sdorra
 */
public class YouTrackSession implements Closeable {

    /** Field description */
    private static final String HEADER_LOCATION = "Location";

    /** Field description */
    private static final String PARAM_DESCRIPTION = "description";

    /** Field description */
    private static final String PARAM_PERMITTED_GROUP = "permittedGroup";

    /** Field description */
    private static final String PARAM_PROJECT = "project";

    /** Field description */
    private static final String PARAM_SUMMARY = "summary";

    //~--- constructors ---------------------------------------------------------

    /**
     * Constructs ...
     *
     *
     * @param client
     * @param baseUrl
     */
    public YouTrackSession(Client client, String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
    }

    //~--- methods --------------------------------------------------------------

    /**
     * Method description
     *
     */
    @Override
    public void close() {
        client.destroy();
    }

    /**
     * Create a new issue.
     *
     * @param input issue input parameters
     *
     * @since 0.2.0
     *
     * @return new created issue
     */
    public Issue createIssue(IssueInput input) {
        String url = URLs.append(baseUrl, "issue");

        WebResource resource = client.resource(url);

        resource = resource.queryParam(PARAM_PROJECT, input.getProject());
        resource = resource.queryParam(PARAM_SUMMARY, input.getSummary());
        resource = resource.queryParam(PARAM_DESCRIPTION, input.getDescription());

        String permittedGroup = input.getPermittedGroup();

        if (!Strings.isNullOrEmpty(permittedGroup)) {
            resource = resource.queryParam(PARAM_PERMITTED_GROUP,
                    input.getPermittedGroup());
        }

        ClientResponse response = resource.put(ClientResponse.class);
        int statusCode = response.getStatus();

        if (statusCode != 201) {
            //J-
            throw new YouTrackException(
                    "youtrack returns status code: ".concat(String.valueOf(statusCode))
            );
            //J+
        }

        String location = response.getHeaders().getFirst(HEADER_LOCATION);

        if (Strings.isNullOrEmpty(location)) {
            throw new YouTrackException("youtrack returns no location header");
        }

        return new Issue(this, client.resource(location).get(IssueDTO.class));
    }

    //~--- get methods ----------------------------------------------------------

    /**
     * Method description
     *
     *
     * @param issueId
     *
     * @return
     */
    public Issue getIssue(String issueId) {
        return new Issue(this, getIssueDTO(issueId));
    }

    /**
     * Method description
     *
     *
     * @param projectid
     *
     * @return
     */
    public List<Issue> getIssuesByProject(String projectid) {
        String url = URLs.append(baseUrl, "issue", "byproject", projectid);
        List<Issue> issues = new ArrayList<Issue>();
        List<IssueDTO> dtos =
                client.resource(url).get(new GenericType<List<IssueDTO>>() {
                                         }
                );

        if (dtos != null) {
            for (IssueDTO dto : dtos) {
                issues.add(new Issue(this, dto));
            }
        }

        return Collections.unmodifiableList(issues);
    }

    //~--- methods --------------------------------------------------------------

    /**
     * Method description
     *
     *
     * @param issueId
     * @param comment
     */
    void addComment(String issueId, String comment) {
        execute(issueId, "comment", comment);
    }

    /**
     * Method description
     *
     *
     * @param issueId
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
    void execute(String issueId, String key, String value) {
        String url = URLs.append(baseUrl, "issue", issueId, "execute");
        WebResource resource = client.resource(url);
        MultivaluedMap formData = new MultivaluedMapImpl();

        formData.putSingle(key, value);

        resource.post(formData);
    }

    /**
     * Method description
     *
     *
     * @param issueId
     * @param command
     */
    void executeCommand(String issueId, String command) {
        execute(issueId, "command", command);
    }

    //~--- get methods ----------------------------------------------------------

    /**
     * Method description
     *
     *
     * @param issueId
     *
     * @return
     */
    IssueDTO getIssueDTO(String issueId) {
        String url = URLs.append(baseUrl, "issue", issueId);
        WebResource resource = client.resource(url);

        return resource.get(IssueDTO.class);
    }

    //~--- fields ---------------------------------------------------------------

    /** Field description */
    private final String baseUrl;

    /** Field description */
    private final Client client;

}
