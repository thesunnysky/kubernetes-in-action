package org.sun.kubernetes.test1;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.*;

import java.util.List;

/**
 * Created on 2017/12/28.
 */
public class ClientTest {
    public static void main(String[] args) {
        String master = "https://192.168.143.141:6443";

//        Config config = new ConfigBuilder().withMasterUrl(master).build();
//        String conf = "G:\\workspace\\kubernetes\\admin.conf";
//        String conf = "G:\\workspace\\admin.conf";
        Config config = new ConfigBuilder()
                .withScaleTimeout(1000)
                .withRollingTimeout(1000)
                .build();
        String conf = "G:\\workspace\\kubernetes\\kubelet.conf";
        String Token = "1971b9d9d7befa55";
        System.setProperty(Config.KUBERNETES_KUBECONFIG_FILE, conf);
        KubernetesClient client = new DefaultKubernetesClient(config);

        try {
            NamespaceList namespaceList = client.namespaces().list();
            List<Namespace> spaceList = namespaceList.getItems();
            for (Namespace item : spaceList) {
                ObjectMeta meta = item.getMetadata();
                System.out.println(meta.getName());
            }

            System.out.println(
//                    client.services().inNamespace("default")
                    client.namespaces().list()
//                    client.getNamespace()
            );

            System.out.println(
                    client.namespaces().withLabel("this", "works").list()
            );

            System.out.println(
                    client.pods().withLabel("this", "works").list()
            );

            System.out.println(
                    client.pods().inNamespace("test").withLabel("this", "works").list()
            );

            System.out.println(
                    client.pods().inNamespace("test").withName("testing").get()
            );
        } catch (KubernetesClientException e) {
            System.out.println(e.getMessage() + ":" + e);
        }
    }
}
