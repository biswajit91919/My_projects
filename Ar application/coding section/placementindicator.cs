using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.XR.ARFoundation;
using UnityEngine.XR.ARSubsystems;
using UnityEngine.Experimental.XR;

public class placementindicator : MonoBehaviour
{
    private ARRaycastManager raymanager;
    public GameObject visual;
    objectspawnner objectspawnner;
    public GameObject loading;

    void Start()
    {
        raymanager = FindObjectOfType<ARRaycastManager>();
        visual = transform.GetChild(0).gameObject;
        objectspawnner = FindObjectOfType<objectspawnner>();

        visual.SetActive(false);

    }

    void Update()
    {


        List<ARRaycastHit> hits = new List<ARRaycastHit>();
        raymanager.Raycast(new Vector2(Screen.width / 2, Screen.height / 2), hits, TrackableType.Planes);

        if(hits.Count > 0)
        {
            transform.position = hits[0].pose.position;
            transform.rotation = hits[0].pose.rotation;

            if (!visual.activeInHierarchy)
            {
                visual.SetActive(true);
                loading.SetActive(false);
            }


        }

        if(objectspawnner.setonce == true)
        {

            visual.SetActive(false);

        }





    }
}
