package com.salhi.customerservice.web.soapapi;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 4.0.0
 * 2025-02-06T14:35:28.400+01:00
 * Generated source version: 4.0.0
 *
 */
@WebService(targetNamespace = "http://soapApi.web.customerservice.salhi.com/", name = "CustomerSoapController")
@XmlSeeAlso({ObjectFactory.class})
public interface CustomerSoapController {

    @WebMethod
    @RequestWrapper(localName = "customerById", targetNamespace = "http://soapApi.web.customerservice.salhi.com/", className = "com.salhi.customerservice.web.soapapi.CustomerById")
    @ResponseWrapper(localName = "customerByIdResponse", targetNamespace = "http://soapApi.web.customerservice.salhi.com/", className = "com.salhi.customerservice.web.soapapi.CustomerByIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.salhi.customerservice.web.soapapi.Customer customerById(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Long arg0
    );

    @WebMethod
    @RequestWrapper(localName = "customerList", targetNamespace = "http://soapApi.web.customerservice.salhi.com/", className = "com.salhi.customerservice.web.soapapi.CustomerList")
    @ResponseWrapper(localName = "customerListResponse", targetNamespace = "http://soapApi.web.customerservice.salhi.com/", className = "com.salhi.customerservice.web.soapapi.CustomerListResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<com.salhi.customerservice.web.soapapi.Customer> customerList()
;

    @WebMethod
    @RequestWrapper(localName = "saveCustomer", targetNamespace = "http://soapApi.web.customerservice.salhi.com/", className = "com.salhi.customerservice.web.soapapi.SaveCustomer")
    @ResponseWrapper(localName = "saveCustomerResponse", targetNamespace = "http://soapApi.web.customerservice.salhi.com/", className = "com.salhi.customerservice.web.soapapi.SaveCustomerResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.salhi.customerservice.web.soapapi.Customer saveCustomer(

        @WebParam(name = "customer", targetNamespace = "")
        com.salhi.customerservice.web.soapapi.CustomerRequest customer
    );
}
