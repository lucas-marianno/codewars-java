class MathEvaluator {
  parens = /(-?)\(([^()]+)\)/;
  divMul = /(-?[0-9.]+)\s*([*/])\s*(-?[0-9.]+)/;
  addSub = /(-?[0-9.]+)\s*([+-])\s*(-?[0-9.]+)/;
  calculate(expression) {
    while (this.parens.test(expression)) {

      const match = expression.match(this.parens);
      if (!match) break;

      const [fullMatch, signBeforeParens, content] = match;
      const start = match.index;
      const end = start + fullMatch.length;

      let evaluatedContent = this.evaluate(content);

      if (signBeforeParens === '-') {

        evaluatedContent = evaluatedContent.startsWith('-')
          ? evaluatedContent.substring(1)
          : '-' + evaluatedContent;
      }

      expression = expression.substring(0, start) + evaluatedContent + expression.substring(end);
    }

    const finalResultStr = this.evaluate(expression);

    return Number(finalResultStr);
  }
  evaluate(expression) {

    const processOperation = (match, xStr, op, yStr) => {
      const x = Number(xStr);
      const y = Number(yStr);
      let result;

      switch (op) {
        case '*':
          result = x * y;
          break;
        case '/':
          result = x / y;
          break;
        case '+':
          result = x + y;
          break;
        case '-':
          result = x - y;
          break;
        default:
          throw new Error('Invalid operator: ' + op);
      }
      return result.toString();
    };
    while (this.divMul.test(expression)) {
      expression = expression.replace(this.divMul, processOperation);
    }
    while (this.addSub.test(expression)) {
      expression = expression.replace(this.addSub, processOperation);
    }
    return expression.trim();
  }
}

const calc = function(expression){
let mathCalc = new MathEvaluator();

  mathCalc.calculate(expression);
};
