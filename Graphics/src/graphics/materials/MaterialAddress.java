/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.materials;

import java.util.Objects;

/**
 *
 * @author Tamaran
 */
public class MaterialAddress {
    
    private String filename, materialname;

    public MaterialAddress(String filename, String materialname) {
        this.filename = filename;
        this.materialname = materialname;
    }

    public String getFilename() {
        return filename;
    }

    public String getMaterialname() {
        return materialname;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.filename);
        hash = 23 * hash + Objects.hashCode(this.materialname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MaterialAddress other = (MaterialAddress) obj;
        if (!Objects.equals(this.filename, other.filename)) {
            return false;
        }
        if (!Objects.equals(this.materialname, other.materialname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MaterialAddress{" + "filename=" + filename + ", materialname=" + materialname + '}';
    }
    
    
    
}
