package org.news.client.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StopWatch;
import org.springframework.util.StreamUtils;

import java.io.*;


public final class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        StopWatch watch = new StopWatch();
        traceRequest(request, body);

        watch.start();

        ClientHttpResponse response = execution.execute(request, body);

        watch.stop();

        return traceResponse(request, response, watch);
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        if (!log.isDebugEnabled()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n===========================request begin================================================");
        sb.append("\nURI         :  " + request.getURI());
        sb.append("\nMethod      :  " + request.getMethod());
        sb.append("\nHeaders     :  " + request.getHeaders());
        sb.append("\nRequest body:  " + new String(body, "UTF-8"));
        sb.append("\n==========================request end================================================");
        log.debug(sb.toString());
    }

    private ClientHttpResponse traceResponse(HttpRequest request, ClientHttpResponse response, StopWatch watch) throws IOException {
        if (!log.isDebugEnabled()) {
            return response;
        }
        final ClientHttpResponse responseWrapper = new BufferingClientHttpResponseWrapper(response);
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseWrapper.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n============================response begin==========================================");
        sb.append("\nURI          : " + request.getURI());
        sb.append("\nStatus code  : " + responseWrapper.getStatusCode());
        sb.append("\nStatus text  : " + responseWrapper.getStatusText());
        sb.append("\nHeaders      : " + responseWrapper.getHeaders());
        sb.append("\nResponse time: " + watch.getTotalTimeMillis() + " ms");
        sb.append("\nResponse body: " + inputStringBuilder.toString());
        sb.append("\n=======================response end=================================================");
        log.debug(sb.toString());
        return responseWrapper;
    }

    public final class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

        private final ClientHttpResponse response;

        private byte[] body;


        BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
            this.response = response;
        }

        public org.springframework.http.HttpStatus getStatusCode() throws IOException {
            return this.response.getStatusCode();
        }

        public int getRawStatusCode() throws IOException {
            return this.response.getRawStatusCode();
        }

        public String getStatusText() throws IOException {
            return this.response.getStatusText();
        }

        public org.springframework.http.HttpHeaders getHeaders() {
            return this.response.getHeaders();
        }

        public InputStream getBody() throws IOException {
            if (this.body == null) {
                this.body = StreamUtils.copyToByteArray(this.response.getBody());
            }
            return new ByteArrayInputStream(this.body);
        }

        public void close() {
            this.response.close();
        }
    }
}
