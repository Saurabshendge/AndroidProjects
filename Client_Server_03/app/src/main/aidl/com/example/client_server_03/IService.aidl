// IService.aidl
package com.example.client_server_03;
import com.example.client_server_03.IServiceCallBack;

// Declare any non-default types here with import statements

interface IService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
void fromActivity(); // function called from activity
    void registerCallBack(IServiceCallBack cb);
}