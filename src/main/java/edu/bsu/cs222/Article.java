package edu.bsu.cs222;

import java.util.List;

public class Article {
    private String title;
    private List<Revision> revisions;

    public Article(String title, List<Revision> revisions) {
        this.title = title;
        this.revisions = revisions;
    }

    public String getTitle() {
        return title;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public Revision getLatestRevision() {
        // Assuming the first revision in the list is the latest
        return revisions.get(0);
    }
}
//{
//  "continue": {
//    "rvcontinue": "20230917134925|1175795183",
//    "continue": "||"
//  },
//  "query": {
//    "redirects": [
//      {
//        "from": "Zappa",
//        "to": "Frank Zappa"
//      }
//    ],
//    "pages": {
//      "10672": {
//        "pageid": 10672,
//        "ns": 0,
//        "title": "Frank Zappa",
//        "revisions": [
//          {
//            "user": "StefenTower",
//            "timestamp": "2023-09-21T08:36:42Z"
//          }
//        ]
//      }
//    }
//  }
//}
