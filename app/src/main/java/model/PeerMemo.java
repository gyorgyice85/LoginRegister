package model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PeerMemo {
//    DateiMemo dateiMemo;

    private long uid;
    private int peerId;
    private String peerIp;
//    private boolean checked;
    private Zone zone;
    private List<PeerMemo> peers = new ArrayList<PeerMemo>();
    private List<Neighbour> neighbours = new ArrayList<Neighbour>();
    private HashSet<Integer> fileId = new HashSet<Integer>();



//    public PeerMemo(long uid, int peerId, String peerIp, boolean checked) {
//        this.uid = uid;
//        this.peerId = peerId;
//        this.peerIp = peerIp;
//        this.checked = checked;
//    }

    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getPeerId() {
        return peerId;
    }

    public void setPeerId(int peerId) {
        this.peerId = peerId;
    }

//    public boolean isChecked() {
//        return checked;
//    }
//
//    public void setChecked (boolean checked) {
//        this.checked = checked;
//    }


    public String getPeerIp() {
        return peerIp;
    }

    public void setPeerIp(String peerIp) {
        this.peerIp = peerIp;
    }


    public int getLength(int zahl){
        String s = String.valueOf(zahl);
        return s.length();
    }


    public int getPeerCount () {
        int length = getLength(peerId);
        int i = 0;
        while (i<length){
            i = i+1;
        }
        return i;
    }

//    public void decreasePeers(){
//        peerId = peerId-1;
//    }

//    public void increasePeers(){
//        peerId = peerId+1;
//    }


    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public List<PeerMemo> getPeers() {
        return peers;
    }

    public void setPeers(List<PeerMemo> peers) {
        this.peers = peers;
    }

    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Neighbour> neighbours) {
        this.neighbours = neighbours;
    }

    public HashSet<Integer> getFileId() {
        return fileId;
    }

    public void setFileNames(HashSet<Integer> fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("");
        if (this.getZone() != null)
            builder.append("Zone : " + this.getZone().toString() + "\n");
        if (this.getFileId() != null) {
            builder.append("Files : ");
            for (Integer i : fileId) {
                builder.append(i + ",");
            }
        }
        builder.setLength(builder.length() - 1);
        builder.append("\n");
        if (this.peers != null) {
            builder.append("Peers : \n");
            builder.append("------------------------------------------------------------------\n");
            for (PeerMemo peer : this.peers) {
                builder.append(peer.getPeerIp() + ",");
            }
            builder.setLength(builder.length() - 1);
        }
        builder.append("\n");
        if (this.neighbours != null) {
            builder.append("Neighbours : \n");
            builder.append("------------------------------------------------------------------\n");
            for (Neighbour neighbour : this.neighbours) {
                builder.append(neighbour.getUid() + ",");
            }
            builder.setLength(builder.length() - 1);
        }
        return builder.toString();
    }
}
