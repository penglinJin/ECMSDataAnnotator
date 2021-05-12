
<a name="paths"></a>
## Resources

<a name="annotator-record-controller_resource"></a>
### Annotator-record-controller
Annotator Record Controller


<a name="approvallistusingget"></a>
#### Get those annotations needed to be approved
```
GET /annotator/annotatorrecord/approvalList
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**params**  <br>*optional*|annotations to be approved|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/annotatorrecord/approvalList
```


###### Request query
```
json :
{
  "params" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="completesituationusingget"></a>
#### Get the annotation completion situation for all docs
```
GET /annotator/annotatorrecord/completeSituation
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/annotatorrecord/completeSituation
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="deleteusingpost"></a>
#### Delete annotation record
```
POST /annotator/annotatorrecord/delete
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**recordIds**  <br>*optional*|annotation record IDs|[Long[]](#long)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/annotatorrecord/delete
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="infousingget"></a>
#### Get annotation information record by ID
```
GET /annotator/annotatorrecord/info/{recordId}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**recordId**  <br>*optional*|annotation record ID|integer (int64)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/annotatorrecord/info/0
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="labelstaticsusingget"></a>
#### Get count statics for annotated labels 
```
GET /annotator/annotatorrecord/labelStatics
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/annotatorrecord/labelStatics
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="listusingget"></a>
#### Get  annotation record list
```
GET /annotator/annotatorrecord/list
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**params**  <br>*optional*|annotation record list|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/annotatorrecord/list
```


###### Request query
```
json :
{
  "params" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="annotationnumusingget"></a>
#### Get count statics for each user's annotation action
```
GET /annotator/annotatorrecord/numStatics
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/annotatorrecord/numStatics
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="saveusingpost"></a>
#### save annotation record
```
POST /annotator/annotatorrecord/save
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**annotatorRecord**  <br>*optional*|annotation record|[AnnotatorRecordEntity](#annotatorrecordentity)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/annotatorrecord/save
```


###### Request body
```
json :
{
  "annotatorTypeCode" : 0,
  "createTime" : "string",
  "docId" : "string",
  "endOffset" : 0,
  "labelId" : 0,
  "newLabels" : "string",
  "oldLabels" : "string",
  "prob" : 0.0,
  "recordId" : 0,
  "startOffset" : 0,
  "status" : 0,
  "targetRecord" : 0,
  "userId" : 0
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="updateusingpost"></a>
#### Update annotation record
```
POST /annotator/annotatorrecord/update
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**annotatorRecord**  <br>*optional*|annotation record update|[AnnotatorRecordEntity](#annotatorrecordentity)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/annotatorrecord/update
```


###### Request body
```
json :
{
  "annotatorTypeCode" : 0,
  "createTime" : "string",
  "docId" : "string",
  "endOffset" : 0,
  "labelId" : 0,
  "newLabels" : "string",
  "oldLabels" : "string",
  "prob" : 0.0,
  "recordId" : 0,
  "startOffset" : 0,
  "status" : 0,
  "targetRecord" : 0,
  "userId" : 0
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="doc-controller_resource"></a>
### Doc-controller
Doc Controller


<a name="approvallistusingget_1"></a>
#### Get documents  needed to be approved
```
GET /annotator/doc/approvalList
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**params**  <br>*optional*|documents  needed to be approved|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/approvalList
```


###### Request query
```
json :
{
  "params" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="approveusingpost"></a>
#### Approve single annotation
```
POST /annotator/doc/approve
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**annotateRecordId**  <br>*required*|annotateRecordId|integer (int64)|
|**Query**|**userId**  <br>*required*|userId|integer (int64)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/approve
```


###### Request query
```
json :
{
  "annotateRecordId" : 0,
  "userId" : 0
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="approvebatchusingpost"></a>
#### Approve multiple annotations
```
POST /annotator/doc/approveBatch
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**userId**  <br>*required*|userId|integer (int64)|
|**Body**|**annotateRecordIds**  <br>*required*|annotateRecordIds|< integer (int64) > array|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/approveBatch
```


###### Request query
```
json :
{
  "userId" : 0
}
```


###### Request body
```
json :
[ 0 ]
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="deleteusingpost_1"></a>
#### Delete documents
```
POST /annotator/doc/delete
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**docIds**  <br>*optional*|Documents IDs|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/delete
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="deletebatchusingpost"></a>
#### deleteBatch
```
POST /annotator/doc/deleteBatch
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**docIds**  <br>*required*|docIds|< integer (int64) > array|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/deleteBatch
```


###### Request body
```
json :
[ 0 ]
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="infousingget_1"></a>
#### Get a document information by ID
```
GET /annotator/doc/info/{docId}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**docId**  <br>*optional*|Document ID|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/info/string
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="listusingget_1"></a>
#### Get All documents
```
GET /annotator/doc/list
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**params**  <br>*required*|params|object|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/list
```


###### Request query
```
json :
{
  "params" : "object"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="prelistusingget"></a>
#### preList
```
GET /annotator/doc/preList
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**params**  <br>*required*|params|object|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/preList
```


###### Request query
```
json :
{
  "params" : "object"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="rejectusingpost"></a>
#### Reject single annotation
```
POST /annotator/doc/reject
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**annotateRecordId**  <br>*required*|annotateRecordId|integer (int64)|
|**Query**|**userId**  <br>*required*|userId|integer (int64)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/reject
```


###### Request query
```
json :
{
  "annotateRecordId" : 0,
  "userId" : 0
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="rejectbatchusingpost"></a>
#### Reject multiple annotations
```
POST /annotator/doc/rejectBatch
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**userId**  <br>*required*|userId|integer (int64)|
|**Body**|**annotateRecordIds**  <br>*required*|annotateRecordIds|< integer (int64) > array|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/rejectBatch
```


###### Request query
```
json :
{
  "userId" : 0
}
```


###### Request body
```
json :
[ 0 ]
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="saveusingpost_1"></a>
#### Save Document
```
POST /annotator/doc/save
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**doc**  <br>*optional*|Document|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/save
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="updateusingpost_1"></a>
#### Update document
```
POST /annotator/doc/update
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**doc**  <br>*optional*|Document|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doc/update
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="doc-label-controller_resource"></a>
### Doc-label-controller
Doc Label Controller


<a name="deleteusingpost_2"></a>
#### Delete document labels
```
POST /annotator/doclabel/delete
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**docLabelIds**  <br>*optional*|document label IDs|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doclabel/delete
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="infousingget_2"></a>
#### Get document annotations by ID
```
GET /annotator/doclabel/info/{docLabelId}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**docLabelId**  <br>*optional*|document annotation's ID|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doclabel/info/string
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="listusingget_2"></a>
#### Get document's annotations list
```
GET /annotator/doclabel/list
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**params**  <br>*optional*|documents' annotations|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doclabel/list
```


###### Request query
```
json :
{
  "params" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="saveusingpost_2"></a>
#### Save new document labels
```
POST /annotator/doclabel/save
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**docLabel**  <br>*optional*|document label|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doclabel/save
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="updateusingpost_2"></a>
#### Update document labels
```
POST /annotator/doclabel/update
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**docLabel**  <br>*optional*|document label|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/doclabel/update
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="doc-state-controller_resource"></a>
### Doc-state-controller
Doc State Controller


<a name="deleteusingpost_3"></a>
#### Delete document states
```
POST /annotator/docstate/delete
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**docStatIds**  <br>*required*|docStatIds|< integer (int64) > array|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/docstate/delete
```


###### Request body
```
json :
[ 0 ]
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="infousingget_3"></a>
#### Get document state by ID
```
GET /annotator/docstate/info/{docStatId}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**docStatId**  <br>*optional*|document state ID|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/docstate/info/string
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="listusingget_3"></a>
#### Get document's states list
```
GET /annotator/docstate/list
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**params**  <br>*optional*|documents' states|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/docstate/list
```


###### Request query
```
json :
{
  "params" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="saveusingpost_3"></a>
#### Save new document state
```
POST /annotator/docstate/save
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**docState**  <br>*required*|docState|[DocStateEntity](#docstateentity)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/docstate/save
```


###### Request body
```
json :
{
  "createTime" : "string",
  "docId" : 0,
  "docStat" : 0,
  "docStatId" : 0,
  "updateTime" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="updateusingpost_3"></a>
#### Update document state
```
POST /annotator/docstate/update
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**docState**  <br>*optional*|document state|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/docstate/update
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="label-info-controller_resource"></a>
### Label-info-controller
Label Info Controller


<a name="deleteusingpost_4"></a>
#### Delete label information
```
POST /annotator/labelinfo/delete
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**labelIds**  <br>*optional*|label IDs|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/labelinfo/delete
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="deletebatchusingpost_1"></a>
#### Delete labels information
```
POST /annotator/labelinfo/deleteBatch
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**labelIds**  <br>*optional*|label IDs|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/labelinfo/deleteBatch
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="infousingget_4"></a>
#### Get label information by label ID
```
GET /annotator/labelinfo/info/{labelId}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**labelId**  <br>*required*|labelId|integer (int64)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/labelinfo/info/0
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="listusingget_4"></a>
#### Get Labels information list
```
GET /annotator/labelinfo/list
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**params**  <br>*optional*|Labels information list|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/labelinfo/list
```


###### Request query
```
json :
{
  "params" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="getoldlabelsusingget"></a>
#### Get the old labels of this doc
```
GET /annotator/labelinfo/oldLabels
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**docId**  <br>*optional*|document ID|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/labelinfo/oldLabels
```


###### Request query
```
json :
{
  "docId" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="saveusingpost_4"></a>
#### Save label information
```
POST /annotator/labelinfo/save
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**labelInfo**  <br>*optional*|label information|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/labelinfo/save
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="updateusingpost_4"></a>
#### Update label information
```
POST /annotator/labelinfo/update
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**labelInfo**  <br>*optional*|label information|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/labelinfo/update
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="src-doc-controller_resource"></a>
### Src-doc-controller
Src Doc Controller


<a name="annotateusingpost"></a>
#### annotate
```
POST /annotator/srcdoc/annotate
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**docId**  <br>*required*|docId|integer (int64)|
|**Query**|**htmlContent**  <br>*required*|htmlContent|string|
|**Query**|**userId**  <br>*required*|userId|integer (int64)|
|**Body**|**labelIds**  <br>*required*|labelIds|< integer (int64) > array|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/srcdoc/annotate
```


###### Request query
```
json :
{
  "docId" : 0,
  "htmlContent" : "string",
  "userId" : 0
}
```


###### Request body
```
json :
[ 0 ]
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="deleteusingpost_5"></a>
#### Delete Source Document information 
```
POST /annotator/srcdoc/delete
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**srcDocIds**  <br>*optional*|source documents ID|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/srcdoc/delete
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="downloadfileusingget"></a>
#### Export annotated files
```
GET /annotator/srcdoc/downloadFile
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[FileSystemResource](#filesystemresource)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/srcdoc/downloadFile
```


##### Example HTTP response

###### Response 200
```
json :
{
  "description" : "string",
  "file" : {
    "absolute" : true,
    "absoluteFile" : {
      "absolute" : true,
      "absoluteFile" : "...",
      "absolutePath" : "string",
      "canonicalFile" : "...",
      "canonicalPath" : "string",
      "directory" : true,
      "file" : true,
      "freeSpace" : 0,
      "hidden" : true,
      "name" : "string",
      "parent" : "string",
      "parentFile" : "...",
      "path" : "string",
      "totalSpace" : 0,
      "usableSpace" : 0
    },
    "absolutePath" : "string",
    "canonicalFile" : "...",
    "canonicalPath" : "string",
    "directory" : true,
    "file" : true,
    "freeSpace" : 0,
    "hidden" : true,
    "name" : "string",
    "parent" : "string",
    "parentFile" : "...",
    "path" : "string",
    "totalSpace" : 0,
    "usableSpace" : 0
  },
  "filename" : "string",
  "inputStream" : { },
  "open" : true,
  "outputStream" : { },
  "path" : "string",
  "readable" : true,
  "uri" : {
    "absolute" : true,
    "authority" : "string",
    "fragment" : "string",
    "host" : "string",
    "opaque" : true,
    "path" : "string",
    "port" : 0,
    "query" : "string",
    "rawAuthority" : "string",
    "rawFragment" : "string",
    "rawPath" : "string",
    "rawQuery" : "string",
    "rawSchemeSpecificPart" : "string",
    "rawUserInfo" : "string",
    "scheme" : "string",
    "schemeSpecificPart" : "string",
    "userInfo" : "string"
  },
  "url" : {
    "authority" : "string",
    "content" : "object",
    "defaultPort" : 0,
    "file" : "string",
    "host" : "string",
    "path" : "string",
    "port" : 0,
    "protocol" : "string",
    "query" : "string",
    "ref" : "string",
    "userInfo" : "string"
  },
  "writable" : true
}
```


<a name="infousingget_5"></a>
#### Get Source Document information by ID
```
GET /annotator/srcdoc/info/{srcDocId}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**srcDocId**  <br>*optional*|source document ID|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/srcdoc/info/string
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="listusingget_5"></a>
#### Get Source Documents list
```
GET /annotator/srcdoc/list
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**params**  <br>*optional*|source documents|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/srcdoc/list
```


###### Request query
```
json :
{
  "params" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="processdatasetusingpost"></a>
#### Process Dataset to small slice
```
POST /annotator/srcdoc/process
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**filePath**  <br>*required*|filePath|string|
|**Query**|**userId**  <br>*required*|userId|integer (int64)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/srcdoc/process
```


###### Request query
```
json :
{
  "filePath" : "string",
  "userId" : 0
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="saveusingpost_5"></a>
#### Save Source Document information 
```
POST /annotator/srcdoc/save
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**srcDoc**  <br>*optional*|New source document|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/srcdoc/save
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="testusingpost"></a>
#### test
```
POST /annotator/srcdoc/test
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/srcdoc/test
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="updateusingpost_5"></a>
#### Update Source Document information 
```
POST /annotator/srcdoc/update
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**srcDoc**  <br>*optional*|updated source document|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/annotator/srcdoc/update
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="sys-user-controller_resource"></a>
### Sys-user-controller
Sys User Controller


<a name="deleteusingpost_6"></a>
#### Delete User information 
```
POST /api/sysuser/delete
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**userIds**  <br>*optional*|Users ID|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/api/sysuser/delete
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="infousingget_6"></a>
#### Get User information by ID
```
GET /api/sysuser/info/{userId}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**userId**  <br>*optional*|User's ID|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/api/sysuser/info/string
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="listusingget_6"></a>
#### Get all User list
```
GET /api/sysuser/list
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**params**  <br>*optional*|All Users|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/api/sysuser/list
```


###### Request query
```
json :
{
  "params" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="saveusingpost_6"></a>
#### Save User information 
```
POST /api/sysuser/save
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**sysUser**  <br>*optional*|User information|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/api/sysuser/save
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="updateusingpost_6"></a>
#### Update User information 
```
POST /api/sysuser/update
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**sysUser**  <br>*optional*|New User information|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
/api/sysuser/update
```


###### Request body
```
json :
{ }
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```



