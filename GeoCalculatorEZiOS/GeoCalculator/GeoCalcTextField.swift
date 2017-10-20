//
//  GeoCalcTextField.swift
//  GeoCalculator
//
//

import UIKit

class GeoCalcTextField: DecimalMinusTextField {

    override func awakeFromNib() {
        self.tintColor = FOREGROUND_COLOR
        self.backgroundColor = UIColor.clear
        self.textColor = FOREGROUND_COLOR
        self.layer.borderWidth = 1.0
        self.layer.borderColor = FOREGROUND_COLOR.cgColor
        
        guard let ph = self.placeholder else {
            return
        }
        
        self.attributedPlaceholder = NSAttributedString(string: ph, attributes: [NSForegroundColorAttributeName: FOREGROUND_COLOR])
    }

}
