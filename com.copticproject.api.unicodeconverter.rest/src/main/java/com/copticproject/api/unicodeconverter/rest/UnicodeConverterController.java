package com.copticproject.api.unicodeconverter.rest;

import com.copticproject.api.unicodeconverter.Converter;
import com.copticproject.api.unicodeconverter.UnsupportedFontException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
//import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;

@Path("/")
public class LegacyFontsConverter {
    @GET
    @Produces("text/plain")
    public String helpText() {
        return "Welcome to Coptic legacy fonts converter to Unicode text.\n" +
                "Use POST to url /text request with parameters 'fontName' and 'sourceText' to get Unicode text.\n" +
                "Or use POST to url /json with a JSON object {\"fontName\": \"something\"," +
                " \"sourceText\": \"something\"} and the response will a JSON object wit one field called 'unicodeText'";
    }

    @OPTIONS
    //@Path("/*")
    public Response getOptions() {
        return createResponseBuiler().build();
    }

    @OPTIONS
    @Path("/text")
    public Response getOptionsForText() {
        return createResponseBuiler().build();
    }

    @OPTIONS
    @Path("/json")
    public Response getOptionsForJson() {
        return createResponseBuiler().build();
    }

    @POST
    @Produces("text/plain")
    @Path("/text")
    public Response doConvertText(@FormParam("fontName") String fontName,
                               @FormParam("sourceText") String sourceText) {
        if (fontName == null || fontName.isEmpty() ||
                sourceText == null || sourceText.isEmpty())
            return Response.noContent().entity("'fontName' and 'sourceText'" +
                    " form parameters cannot be empty").build();

        Converter converter = new Converter();
        try {
            return createResponseBuiler().entity(converter.convert(fontName, sourceText)).build();
        } catch (UnsupportedFontException e) {
            return Response.serverError().entity("Unknown font type").build();
        }
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/json")
    public Response doConvertJson(ConvertRequest request) {
        if (request.getFontName() == null || request.getFontName().isEmpty() ||
                request.getSourceText() == null || request.getSourceText().isEmpty())
            return Response.noContent().entity("'fontName' and 'sourceText'" +
                    " form parameters cannot be empty").build();

        ConvertResponse response = new ConvertResponse();

        try {
            Converter converter = new Converter();
            response.setUnicodeText(converter.convert(request.getFontName(),
                    request.getSourceText()));
        } catch (UnsupportedFontException e) {
            return Response.serverError().entity("Unknown font type").build();
        }

        return createResponseBuiler().entity(response).build();
    }

    private Response.ResponseBuilder createResponseBuiler() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
    }
}