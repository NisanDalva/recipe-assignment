import React, { useState } from "react";

const Recipe = ({ recipeId, title, summary, analyzedInstructions, image }) => {
  const [checkedValue, setCheckedValue] = useState(false);
  const [indicateFavorite, setIndicateFavorite] = useState("Add to Favorite");

  const handleCheckbox = (e) => {
    setCheckedValue(!checkedValue);

    if (!checkedValue) {
      setIndicateFavorite("Remove From Favorite");
    } else {
      setIndicateFavorite("Add to Favorite");
    }
  };

  let instructions_title = analyzedInstructions.length !== 0 ? "Instructions:" : "No instrautions were given";

  let instructions = "<ol>";
  for (var elm of analyzedInstructions) {
    for (var s of elm.steps) {
      instructions += "<li>" + s.step + "</li>";
    }
  }
  instructions += "</ol>";

  const handleFavorites = (e) => {
    const path = !checkedValue ? "addToFavorites" : "removeFromFavorites";
    const method = !checkedValue ? "POST" : "PUT";

    fetch(path, {
      method: method,
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({ id: recipeId }),
    });
  };

  return (
    <div>
      <h1>{title}</h1>
      <p dangerouslySetInnerHTML={{ __html: summary }}></p>
      <h3>{instructions_title}</h3>
      <p dangerouslySetInnerHTML={{ __html: instructions }}></p>
      <img src={image} alt="" />
      <label>
        <input
          type="checkbox"
          onChange={handleCheckbox}
          onClick={handleFavorites}
          checked={checkedValue}
        />
        {indicateFavorite}
      </label>
    </div>
  );
};

export default Recipe;
