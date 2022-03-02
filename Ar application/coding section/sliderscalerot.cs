using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class sliderscalerot : MonoBehaviour
{

    private Slider scaleslider, rotationslider;

    public float scaleminvalue, scalemaxvalue, rotminvalue, rotmaxvalue;


    // Start is called before the first frame update
    void Start()
    {
        scaleslider = GameObject.Find("scale").GetComponent<Slider>();
        scaleslider.maxValue = scalemaxvalue;
        scaleslider.minValue = scaleminvalue;

        scaleslider.onValueChanged.AddListener(scalesliderupdate);

        rotationslider = GameObject.Find("rotate").GetComponent<Slider>();
        rotationslider.maxValue = rotmaxvalue;
        rotationslider.minValue = rotminvalue;

        rotationslider.onValueChanged.AddListener(rotatesliderupdate);


    }


    void scalesliderupdate(float value)
    {

        transform.localScale = new Vector3(value, value, value);

    }

    void rotatesliderupdate(float value)
    {
        transform.localEulerAngles = new Vector3(transform.rotation.x, value, transform.rotation.z);
    }





    // Update is called once per frame
    void Update()
    {
        
    }
}
