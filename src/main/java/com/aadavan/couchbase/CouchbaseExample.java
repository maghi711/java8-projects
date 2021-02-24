package com.aadavan.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;

public class CouchbaseExample {

    private static String bucket;

    public static void main(String[] args) {
        // Initialize the connection
        Cluster cluster = CouchbaseCluster.create("192.168.106.69");
        cluster.authenticate("Administrator", "password");

        CouchbaseExample.bucket = "trident_main";
        Bucket bucket = cluster.openBucket(CouchbaseExample.bucket);

        // Perform a N1QL Query
        N1qlQueryResult result = bucket.query(
            //N1qlQuery.parameterized("SELECT id FROM trident_main USE KEYS 'P::8198::3DS::1612865014659'", JsonArray.empty()));
            N1qlQuery.parameterized("UPDATE trident_main USE KEYS 'P::8198::3DS::1614002665848' SET activeVersion = 10", JsonArray.empty()));

        if (result != null && result.finalSuccess() && result.info().mutationCount() == 1) {
            System.out.println("update successful.");
        }
        // Print each found Row
        for (N1qlQueryRow row : result) {
            // Prints {"name":"Arthur"}
            System.out.println(row);
        }
    }
}
