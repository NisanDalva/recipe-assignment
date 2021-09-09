import React, { useState } from "react";

const Recipe = ({ recipeId, title, summary, analyzedInstructions, image }) => {
  const [checkedValue, setCheckedValue] = useState(false);
  const [indicateFavorite, setIndicateFavorite] = useState("Add to Favorite");

  const handleCheckbox = (e) => {
    setCheckedValue(!checkedValue);

    if (!checkedValue) {
      console.log(`${title} added to favorites`);
      setIndicateFavorite("Remove From Favorite");
    } else {
      console.log(`${title} removed to favorites`);
      setIndicateFavorite("Add to Favorite");
    }
  };

  let instructions_title = analyzedInstructions.length !== 0 ? "Instructions:" : "No instrautions were given";

  let instructions = "<ol>";
  for (var elm of analyzedInstructions) {
    // console.log(elm.steps);
    // console.log(Array.isArray(`is elm.steps array? ${elm.steps}`))
    for (var s of elm.steps) {
      // console.log(s.step);
      instructions += "<li>" + s.step + "</li>";
    }
  }
  instructions += "</ol>";

  // for(let i = 0; i < 2; i++) {
  //   console.log(`${analyzedInstractions[i]}`);
  // }

  // console.log(`is array: ${Array.isArray(analyzedInstractions)}`)

  // for (const property in analyzedInstractions) {
  //   console.log(`${property}: ${analyzedInstractions[property]}`);
  // }

  // console.log(`${analyzedInstractions.steps}`);
  // Object.keys(analyzedInstractions).forEach((name)=> console.log(name));
  // console.log(`recipe title ${title} --- len of analyzed = ${analyzedInstractions.length}`);
  // console.log(`recipe title ${title} --- analyzedInstractions[0] = ${analyzedInstractions[0]}`);

  // for (var elm of analyzedInstractions) {
  //   // console.log(elm.steps);
  //   // console.log(Array.isArray(`is elm.steps array? ${elm.steps}`))
  //   for (var s of elm.steps){
  //     console.log(s.step);
  //   }
  // }

  // console.log(`FINAL INSTRACTIONS = ${instractions}`);

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
      {/* <p>{ingredients}</p> */}
      {/* <p>{summary}</p> */}
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
