/**
 * The MIT License
 *
 * Copyright (c) 2013, Sebastian Sdorra
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
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

import sonia.youtrack.internal.URLs;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.ApacheHttpClientConfig;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Sebastian Sdorra
 */
public class YouTrack
{

  /** Field description */
  private static final String PROPERTY_DEBUG = "sonia.youtrack.debug";

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param baseUrl
   * @param username
   * @param password
   *
   * @return
   */
  @SuppressWarnings("unchecked")
  public static YouTrackSession createSession(String baseUrl, String username,
    String password)
  {
    ApacheHttpClientConfig config = new DefaultApacheHttpClientConfig();

    config.getProperties().put(ApacheHttpClientConfig.PROPERTY_HANDLE_COOKIES,
      true);

    Client client = ApacheHttpClient.create(config);

    if (Boolean.getBoolean(PROPERTY_DEBUG))
    {
      client.addFilter(new LoggingFilter(System.out));
    }

    String restUrl = URLs.append(baseUrl, "rest");
    String loginUrl = URLs.append(restUrl, "user", "login");

    WebResource resource = client.resource(loginUrl);

    MultivaluedMap formData = new MultivaluedMapImpl();

    formData.putSingle("login", username);
    formData.putSingle("password", password);

    resource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);

    resource.post(formData);

    return new YouTrackSession(client, restUrl);
  }
}
