package net.likeyun.base.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import net.likeyun.base.error_result.GraphQLErrorResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphQLCustomException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    private Integer code;

    public GraphQLCustomException(GraphQLErrorResult result) {
        super(result.getDesc());
        this.code = result.getValue();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        extensions.put("code", code);
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
