#/bin/bash
#生成es相关的person索引
escluster_host=$1
curl -XPOST http://${escluster_host}:9200/people_index/fulltext/_mapping -d'
{
    "fulltext": {
        "_all": {
            "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
            "term_vector": "no",
            "store": "false"
        },
        "properties": {
            "id": {
                "type": "string"
            },
            "type": {
                "type": "string"
            },
            "subtype": {
                "type": "string"
            },
            "hostname": {
                "type": "string"
            },
            "hostid": {
                 "type": "string"
            },
            "hostphone": {
                 "type": "string"
            },
            "number": {
                 "type": "string"
            },
            "lessee": {
                 "type": "string"
            },
            "stay": {
                 "type": "string"
            },
            "employee": {
                 "type": "string"
            },
            "expend": {
                 "type": "string"
            }
        }
    }
}'
#房屋相关
curl -XPOST http://${escluster_host}:9200/house_index/fulltext/_mapping -d'
{
    "fulltext": {
        "_all": {
            "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
            "term_vector": "no",
            "store": "false"
        },
        "properties": {
            "id": {
                "type": "string"
            },
            "type": {
                "type": "string"
            },
            "subtype": {
                "type": "string"
            },
            "location": {
                 "type": "string",
                 "term_vector": "with_positions_offsets",
                 "analyzer": "ik_max_word",
                 "search_analyzer": "ik_max_word",
                 "boost": 8
            },
            "host": {
                 "type": "string"
            },
            "owner": {
                 "type": "string"
            },
            "ownerid": {
                 "type": "string"
            },
            "ownerphone": {
                 "type": "string"
            },
            "unit": {
                 "type": "string"
            },
            "floor": {
                 "type": "string"
            },
            "doornumber": {
                 "type": "string"
            },
            "employee": {
                 "type": "string"
            },
            "expend": {
                 "type": "string"
            }
        }
    }
}'
#雇佣关系相关索引结构
curl -XPOST http://${escluster_host}:9200/employer_index/fulltext/_mapping -d'
{
    "fulltext": {
        "_all": {
            "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
            "term_vector": "no",
            "store": "false"
        },
        "properties": {
            "id": {
                "type": "string"
            },
            "type": {
                "type": "string"
            },
            "name": {
                "type": "string"
            },
            "address": {
                 "type": "string",
                 "term_vector": "with_positions_offsets",
                 "analyzer": "ik_max_word",
                 "search_analyzer": "ik_max_word",
                 "boost": 8
            },
            "chargeman": {
                 "type": "string"
            },
            "chargemanid": {
                 "type": "string"
            },
            "chargemanphone": {
                 "type": "string"
            },
            "safeman": {
                 "type": "string"
            },
            "safemanid": {
                 "type": "string"
            },
            "safemanphone": {
                 "type": "string"
            },
            "extend": {
                 "type": "string"
            }
        }
    }
}'
#场所
curl -XPOST http://${escluster_host}:9200/house_index/fulltext/_mapping -d'
{
    "fulltext": {
        "_all": {
            "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
            "term_vector": "no",
            "store": "false"
        },
        "properties": {
            "id": {
                "type": "string"
            },
            "type": {
                "type": "string"
            },
            "name": {
                "type": "string"
            },
            "address": {
                 "type": "string",
                 "term_vector": "with_positions_offsets",
                 "analyzer": "ik_max_word",
                 "search_analyzer": "ik_max_word",
                 "boost": 8
            },
            "area": {
                 "type": "string"
            },
            "lessor": {
                 "type": "string"
            },
            "lessorid": {
                 "type": "string"
            },
            "lessorphone": {
                 "type": "string"
            },
            "lessee": {
                 "type": "string"
            },
            "lesseeid": {
                 "type": "string"
            },
            "lesseephone": {
                 "type": "string"
            },
            "extend": {
                 "type": "string"
            }
        }
    }
}'

#监控相关
curl -XPOST http://${escluster_host}:9200/house_index/fulltext/_mapping -d'
{
    "fulltext": {
        "_all": {
            "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
            "term_vector": "no",
            "store": "false"
        },
        "properties": {
            "id": {
                "type": "string"
            },
            "deviceid": {
                "type": "string"
            },
            "type": {
                "type": "string"
            },
            "policestation": {
                 "type": "string",
                 "term_vector": "with_positions_offsets",
                 "analyzer": "ik_max_word",
                 "search_analyzer": "ik_max_word",
                 "boost": 8
            },
            "localname": {
                 "type": "string"
            },
            "devicetype": {
                 "type": "string"
            },
            "direction": {
                 "type": "string"
            },
            "count": {
                 "type": "string"
            },
            "expend": {
                 "type": "string"
            }
        }
    }
}'

