import React from "react";
import Group1 from "../../../assets/images/Group1.svg";
import Group2 from "../../../assets/images/Group2.svg";
import Group5 from "../../../assets/images/Group5.svg";
import Group9 from "../../../assets/images/Group9.svg";
import Line1 from "../../../assets/images/Line1.svg";
import Line2 from "../../../assets/images/Line2.svg";

import "./bottleIndicator.style.css";
export const BottleIndicator = ({ screenName }) => {
  if (screenName === "PersonalData") {
    return (
      <div className="bottleIndicator">
        <img src={Group1} alt="middle bottle" />
        <img src={Line1} alt="dotted line" />
        <img src={Group2} alt="empty bottle" />
        <img src={Line1} alt="dotted line" />
        <img src={Group2} alt="empty bottle" />
      </div>
    );
  } else if (screenName === "AcademicsData") {
    return (
      <div className="bottleIndicator">
        <img src={Group1} alt="middle bottle" />
        <img src={Line2} alt="dotted line" />
        <img src={Group5} alt="empty bottle" />
        <img src={Line1} alt="dotted line" />
        <img src={Group2} alt="empty bottle" />
      </div>
    );
  } else if (screenName === "MatterChoicer") {
    return (
      <div className="bottleIndicator">
        <img src={Group1} alt="middle bottle" />
        <img src={Line2} alt="dotted line" />
        <img src={Group5} alt="empty bottle" />
        <img src={Line1} alt="dotted line" />
        <img src={Group9} alt="empty bottle" />
      </div>
    );
  }
};
