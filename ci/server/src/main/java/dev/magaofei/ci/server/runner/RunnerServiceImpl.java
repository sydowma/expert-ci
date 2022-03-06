package dev.magaofei.ci.server.runner;

import dev.magaofei.ci.server.node.Node;
import dev.magaofei.ci.server.node.NodeRepository;

public class RunnerServiceImpl implements RunnerService {

    private final NodeRepository nodeRepository;

    public RunnerServiceImpl(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public Object uploadNode(NodeInfo nodeInfo) {
        Node node = new Node();

//        this.nodeRepository.save()
        return null;
    }


}
